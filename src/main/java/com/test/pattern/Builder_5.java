package com.test.pattern;

public class Builder_5 {
    public static void main(String[] args) {
        User user = User.builder().name("foo")
                .password("123456")
                .age(100).build();
        System.out.println(user);
    }

}

class User{
    private String name;
    private String password;
    private String nickname;
    private Integer age;
    private User(String name,String password, String nickname, Integer age){
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.age = age;
    }
    public static UserBuilder builder() {
        return new UserBuilder();

    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                '}';
    }

    public static class UserBuilder {
        private String name;
        private String password;
        private String nickname;
        private Integer age;

        private UserBuilder() {

        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public User build() {
            if (name == null || password == null) {
                throw new RuntimeException("用户名和密码必填");
            }
            if (age <= 0 || age >= 150) {
                throw new RuntimeException("年龄不合法");
            }
            if (nickname == null) {
                nickname = name;
            }
            return new User(name, password, nickname, age);
        }
    }
}
