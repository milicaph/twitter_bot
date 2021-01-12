package properties;

public class Parameters {
    public static final String url = "https://twitter.com/login";
    // this is where you will input your credentials
    public static final String username = "TTestko";
    public static final String password = "mojpinkod2318";

    // this is where excel file with input and output sheet is
    public static final String excelFileLocation = "src/main/resources/twitter.xlsx";
    private static String newline = System.getProperty("line.separator");

    public static  String messageToSend(String username, String game) {
        return "Hey " + username + "!!" + newline +
                newline +
                "    I saw your stream and I love how you are with your community! I work with POQ and our platform allows you to create custom tournaments for your community. " +
                "The platform is completely FREE to use!! We offer sponsored prizes for you to do these tournaments at no cost to yourself." + newline +
                newline +
                "    POQ is currently offering sponsorship opportunities for people who create content for " + game + " and so I thought you would be a great fit!! Check out our website" + newline +
                newline +
                "    https://www.poq.gg/" + newline +
                newline +
                "    https://creators.poq.gg/" + newline +
                newline +
                "    I am happy to answer any questions you have, just hit me up with a DM or you can contact me directly on discord @ruthwolfryd#3810" + newline +
                newline +
                "    Look forward to talking more soon.  ";

    }
}