package com.fir.deer;

import com.fir.deer.dfa.DfaTool;
import com.fir.deer.service.NewRolesService;

/**
 * Created by havens on 16-5-14.
 */
public class DFATest {
    public static void main(String[] args){
        String roleName="操";
        NewRolesService.MyDFAOutCallBack myDFAOutCallBack = new NewRolesService.MyDFAOutCallBack();
        DfaTool.removeKeyword.Search(roleName, myDFAOutCallBack);
        System.out.println(roleName);
        for (String keyword : myDFAOutCallBack.words) {
            System.out.println(keyword);
            roleName = roleName.replaceAll(keyword, "*");
        }
        System.out.println(roleName);
    }
}
