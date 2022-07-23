package com.test.pattern;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

// tomcat中的过滤器即是这种模式，以链的形式依次过滤多种情况
public class ChainTest_15 {
    public static void main(String[] args) {
        MemberService memberService = new MemberService();
        memberService.login("zhu","123456");

    }
}
class MemberService{
    public void login(String name, String password){
        ValidateHandle validateHandle = new ValidateHandle();
        LoginHandler loginHandler = new LoginHandler();
        AuthHandler authHandler = new AuthHandler();

        validateHandle.next(loginHandler);
        loginHandler.next(authHandler);

        validateHandle.doHandler(new Member(name,password));
    }
}



@Data
class Member{
    private String loginName;
    private String loginPassword;
    private String roleName;
    public Member(String loginName, String loginPassword){
        this.loginName = loginName;
        this.loginPassword = loginPassword;
    }
}


// 每个步骤的节点，串联起来
abstract class Handler<T>{
    protected Handler next;

    public void next(Handler next){
        this.next = next;
    }
    public abstract void doHandler(Member member);

}
class ValidateHandle extends Handler{
    public void doHandler(Member member){
        if(StringUtils.isEmpty(member.getLoginName()) || StringUtils.isEmpty(member.getLoginPassword())){
            System.out.println("用户名或者密码为空");
            return;
        }else{
            System.out.println("用户名和密码正常，go on");
        }
        next.doHandler(member);
    }
}
class LoginHandler extends Handler{
    public void doHandler(Member member){
        System.out.println("登录成功");
        member.setRoleName("管理员");
        next.doHandler(member);
    }
}
class AuthHandler extends Handler{
    public void doHandler(Member member){
        if(member.getRoleName().equals("管理员")){
            System.out.println("授权成功，go on");
        }else {
            System.out.println("授权不成功");
            return;
        }
    }
}
