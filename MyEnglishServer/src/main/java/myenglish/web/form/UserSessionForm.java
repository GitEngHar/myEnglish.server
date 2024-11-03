package myenglish.web.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UserSessionForm implements Serializable {

    private String sessionId;

    private String creationTime;

    private String lastAccessedTime;

    private String maxIdleTime;

    private Integer userId;

    private String name;

}
