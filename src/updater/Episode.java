/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updater;

/**
 *
 * @author Alessandro
 */
public class Episode implements Comparable<Episode> {

    private final String title;
    private final String time;
    private final String url;
    private final String language;
    private final String episodeNumber;
    private final String episodeName;
    private final String fullXMLString;
    private final Controller controller;

    public Episode(String inputString, String time, String url) {
        controller = Controller.getInstance();
        fullXMLString = inputString;
        language = controller.getfromInput(inputString, "(?<=\\[).*?(?=\\])");
        title = controller.getfromInput(inputString, "(?<=.*\\] ).*?(?=S[0-9]{2}E[0-9]{2})").replace(".", " ");
        episodeNumber = controller.getfromInput(inputString, "S[0-9]{2}E[0-9]{2}");
        episodeName = controller.getfromInput(inputString, "(?<=S[0-9]{2}E[0-9]{2}).*?(?i)(?=German|720|1080|REPACK|HDTV|DIRFIX|DL)").replace(".", " ");
        this.time = time;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }

    public String getLanguage() {
        return language;
    }

    public String getEpisodeNumber() {
        return episodeNumber;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    private String getFullXMLString() {
        return fullXMLString;
    }

    @Override
    public int compareTo(Episode ep) {
        return this.fullXMLString.compareTo(ep.getFullXMLString());
    }

    @Override
    public String toString() {
        return (this.getLanguage() + " " + this.getTitle() + " " + this.getEpisodeNumber() + " " + this.getEpisodeName());
    }
}
