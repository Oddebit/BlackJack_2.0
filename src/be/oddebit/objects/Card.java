package be.oddebit.objects;

public class Card {

    private String face;
    private int value;


    public Card(int number) {

        setValue(number);
        setFace(number);
    }


    public int getValue() {
        return value;
    }

    public String getFace() {
        return face;
    }


    private void setValue(int number) {

        switch (number) {

            case 1:
                value = 11;
                break;

            case 11:
            case 12:
            case 13:
                value = 10;
                break;

            default:
                value = number;
                break;
        }
    }

    private void setFace(int number) {

        switch (number) {

            case 1:
                face = "A";
                break;

            case 11:
                face = "J";
                break;

            case 12:
                face = "Q";
                break;

            case 13:
                face = "K";
                break;

            default:
                face = String.valueOf(number);
                break;
        }
    }
}
