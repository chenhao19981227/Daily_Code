package reconstruction.chapter8.move_statements_into_function;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MSIF {
    public List<String> renderPerson(Person person){
        List<String> result = new ArrayList<>();
        result.add("<p>${person.name}</p>");
        result.add("<p>title:" + person.getPhoto().getTitle() + "</p>");
        result.add(emitPhotoData(person.getPhoto()));
        return result;
    }
    public String photoDiv(Photo photo){
        List<String> result = Arrays.asList("<div>", "<p>title: " + photo.getTitle() + "</p>", emitPhotoData(photo), "</div>");
        return String.join(",", result);
    }

    public String emitPhotoData(Photo photo) {
        List<String> result=new ArrayList<>();
        result.add("<p>location:"+photo.getLocation()+"</p>");
        return String.join("\n", result);
    }

}
