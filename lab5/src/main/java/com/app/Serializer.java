package com.app;

import com.app.annotations.XmlAttribute;
import com.app.annotations.XmlObject;
import com.app.annotations.XmlTag;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;


public class Serializer {
    public void serialize(Set<Object> objects) throws Exception {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("data");
        for (Object object : objects) {
            if (object.getClass().getAnnotation(XmlObject.class) != null) {
                    serializeObject(object, object.getClass(),  root);

            }

        }
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(System.out, format);
            writer.write(document);

            XMLWriter fileWriter = new XMLWriter(new FileOutputStream("Result.xml"), format);
            fileWriter.write(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Element serializeObject(Object object, Class clazz, Element root) throws Exception {
        Annotation annotation = clazz.getDeclaredAnnotation(XmlObject.class);

        if (annotation == null){
            System.out.println(object + " doesn't serializable in XML");
            return null;
        }

        Element element = root.addElement(clazz.getSimpleName().toLowerCase());

        HashSet<AccessibleObject> attributes = new HashSet<>();

        //Для сериализации предков
        if (clazz.getSuperclass().getDeclaredAnnotation(XmlObject.class) != null && clazz.getDeclaredAnnotation(XmlObject.class) != null) {
            Element e = (Element) serializeObject(object, clazz.getSuperclass(),  element).detach();
            element.appendAttributes(e);
            element.appendContent(e);
        }

        /**
         * Добавление всех тегов в документ, а все поля с атрибутами в набор
         */
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getAnnotation(XmlTag.class) != null) {
                String tagName = field.getAnnotation(XmlTag.class).name();
                if (tagName.equals(""))
                    tagName = field.getName();
                try {
                    field.setAccessible(true);
                    Object o = field.get(object);
                    if (o != null)
                        if (field.getType().getDeclaredAnnotation(XmlObject.class) != null){
                            Element e = (Element) serializeObject(o, o.getClass(), root).detach();
                            e.setName(tagName);
                            element.add(e);
                        }
                        else
                            element.addElement(tagName).addText(field.get(object).toString());

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (field.getAnnotation(XmlAttribute.class) != null)
                attributes.add(field);
        }

        /**
         * Добавление всех тегов в документ, а все методы с атрибутами в набор
         */

        for (Method method : clazz.getDeclaredMethods()) {

            if (method.getAnnotation(XmlTag.class) != null) {
                checkMethod(method);
                String tagName = method.getAnnotation(XmlTag.class).name();
                try {
                    method.setAccessible(true);
                    if (tagName.equals("")) {
                        String mName = method.getName();
                        tagName = mName.startsWith("get") ? mName.substring(3).toLowerCase()
                                : mName.toLowerCase();
                    }
                    Object o = method.invoke(object);
                    if (o != null){
                        if (method.getReturnType().getAnnotation(XmlObject.class) != null)
                            serializeObject(o, o.getClass(), element);
                        else
                            element.addElement(tagName).addText(o.toString());
                    }else element.addElement(tagName).addText("null");

                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (method.getAnnotation(XmlAttribute.class) != null){
                checkMethod(method);
                attributes.add(method);
            }
        }


        /**
         * Добавление всех атрибутов
         */
        for (AccessibleObject o : attributes){
            String tagName = o.getAnnotation(XmlAttribute.class).tag();
            o.setAccessible(true);
            Element tag = element;
            if (!tagName.equals(""))
                tag = (Element) element.selectSingleNode(tagName);

            String atrName = "";
            String atrValue = "";
            try{
                if (o instanceof Field){
                    Field field = (Field) o;
                    atrName = field.getName();
                    atrValue = field.get(object).toString();
                }else if (o instanceof Method){
                    Method method = (Method) o;
                    atrName = method.getName();
                    atrValue = method.invoke(object).toString();
                    atrName = atrName.startsWith("get")
                            ? atrName.substring(3).toLowerCase()
                            : atrName;
                }
                tag.addAttribute(atrName, atrValue);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        return element;
    }

    private void checkMethod(Method method) throws Exception{
        if (method.getParameterCount() != 0)
            throw new Exception("Метод должен быть без параметров: " + method);
        if (method.getReturnType().getSimpleName().toLowerCase().equals("void"))
            throw new Exception("Метод должен возвращать значение: " + method);
    }
}
