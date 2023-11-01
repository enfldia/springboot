package hello.hellospring1.controller;

public class MemberForm {
    private  String name;
    private String nickname;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
