package com.comli.dawnbreaksthrough.personalintro;

/**
 * Created by SparklingGT on 9/12/2016.
 */
public class Elements
{
    public final static class Gender
    {
        public final static int MALE = -1;
        public final static int OTHER = 100;
        public final static int FEMALE = 1;
    }

    public final static class Age
    {
        public final static long TWO_HUNDRED_YEARS = (long) 200 * 365 * 24 * 60 * 60 * 1000L;
        public final static long ONE_HUNDRED_N_FIFTY_YEARS = (long) 150 * 365 * 24 * 60 * 60 * 1000L;
    }

    public final static class Databases
    {
        public final static class PersonTable
        {
            public final static String NAME = "person";

            public final static class Cols
            {
                public final static String UUID = "uuid";
                public final static String NAME = "name";
                public final static String GENDER = "gender";
                public final static String BIRTH = "birth";
                public final static String INTRO = "intro";

            }
        }

    }

    public final static class Thumbnail
    {
        public final static class Size
        {
            public final static int LARGE = 7531;
            public final static int SMALL = 324;
        }
    }
}
