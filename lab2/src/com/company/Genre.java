package com.company;


public enum Genre {
    ROCK{
        public Genre[] getChildren(){
            return new Genre[]{HARDROCK, ALTERNATIVEROCK};
        }
    },
    HARDROCK{
        public Genre[] getChildren() {
            return new Genre[0];
        }
    },
    ALTERNATIVEROCK {
        public Genre[] getChildren() {
            return new Genre[0];
        }
    },
    RAP {
        public Genre[] getChildren() {
            return new Genre[]{JAZZRAP};
        }
    },
    JAZZ {
        public Genre[] getChildren() {
            return new Genre[]{JAZZRAP};
        }
    },
    JAZZRAP{
        public Genre[] getChildren() {
            return new Genre[0];
        }
    },
    NOGENRE{
        public Genre[] getChildren() {
            return new Genre[0];
        }
    };
    public abstract Genre[] getChildren();

    @Override
    public String toString() {
        return "Genre: " + super.toString();
    }
}
