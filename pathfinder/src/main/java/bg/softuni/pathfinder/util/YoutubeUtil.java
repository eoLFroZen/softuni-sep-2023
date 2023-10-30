package bg.softuni.pathfinder.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeUtil {

    public static String getUrl(String fullVideoUrl){
        String regex = "http(?:s?):\\/\\/(?:www\\.)?youtu(?:be\\.com\\/watch\\?v=|\\.be\\/)([\\w\\-\\_]*)(&(amp;)?\u200C\u200B[\\w\\?\u200C\u200B=]*)?";
        Pattern compile = Pattern.compile(regex);

        Matcher matcher = compile.matcher(fullVideoUrl);
        if (matcher.find()) {
            return  matcher.group(1);
        }

        return null;
    }
}
