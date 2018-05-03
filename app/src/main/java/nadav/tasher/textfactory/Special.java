package nadav.tasher.textfactory;

public class Special {
    public static SpecialCharacter[] specialCharacters = {
            new SpecialCharacter("Right-to-Left Mark", '\u200F'),
            new SpecialCharacter("Left-to-Right Mark", '\u200E'),
            new SpecialCharacter("En Space", '\u2002'),
            new SpecialCharacter("Em Space", '\u2003'),
            new SpecialCharacter("Figure Space", '\u2007'),
            new SpecialCharacter("Punctuation Space", '\u2008'),
            new SpecialCharacter("Thin Space", '\u2009'),
            new SpecialCharacter("Hair Space", '\u200A'),
            new SpecialCharacter("Eternity Sign", '\u058D'),
            new SpecialCharacter("Sinhala Punctuation Kunddaliya", '\u0DF4'),
            new SpecialCharacter("Fongman", '\u0E4F'),
            new SpecialCharacter("Tibetan Mark Caret Yig Mgo Phur Shad Ma", '\u0F06'),
            new SpecialCharacter("Cherokee Letter Wi", '\u13EB'),
            new SpecialCharacter("Tennis Ball", '\u1CC2'),
            new SpecialCharacter("Dotted Cross", '\u205C'),
            new SpecialCharacter("Script Capital M", '\u2133'),
    };
    public static Malware[] malwares = {
            new Malware("(([Hh][Tt][Tt][Pp])://)", "https://", "Non Encrypted Traffic", "http:// instead of https://. This is insecure connection."),
            new Malware("\u0c1c+\u0c4d+\u0c1e+\u0c3e", "", "Indian / Telugu Character Bug", "iOS bug that causes crashes"),
            new Malware("\uD83C\uDFF3️0\uD83C\uDF08", "", "Magic Rainbow Bug", "iOS bug that causes crashes")
    };

    static class SpecialCharacter {
        private char special;
        private String desc;

        public SpecialCharacter(String description, char ch) {
            desc = description;
            special = ch;
        }

        public char getChar() {
            return special;
        }

        public String getDescription() {
            return desc;
        }
    }

    static class Malware {
        private String m, r, n, d;

        public Malware(String malware, String replacement, String name, String descript) {
            m = malware;
            r = replacement;
            n = name;
            d = descript;
        }

        public String getMalware() {
            return m;
        }

        public String getReplacement() {
            return r;
        }

        public String getName() {
            return n;
        }

        public String getDescription() {
            return d;
        }
    }
}
