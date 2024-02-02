package reconstruction.chapter8.move_statements_into_function;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AfterMSIF {
    public List<String> renderPerson(Person person){
        List<String> result = new ArrayList<>();
        result.add("<p>${person.name}</p>");
        result.add(emitPhotoData(person.getPhoto()));
        return result;
    }
    public String photoDiv(Photo photo){
        List<String> result = Arrays.asList("<div>", emitPhotoData(photo), "</div>");
        return StringUtils.join(result, ",");
    }

    public String emitPhotoData(Photo photo) {
        List<String> result=new ArrayList<>();
        result.add("<p>title:" + photo.getTitle() + "</p>, "+"<p>location:"+photo.getLocation()+"</p>");
        return StringUtils.join(result,"\n");
    }
}
