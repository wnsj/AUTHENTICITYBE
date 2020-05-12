package com.jiubo.buildstore.service;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.AccountBean;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author mwl
 * @since 2020-02-10
 */
public interface AccountService {

    //查询账号
    public List<AccountBean> queryAccountList(AccountBean accountBean) throws Exception;

    public JSONObject login(AccountBean accountBean) throws Exception;

    //添加账号
    public AccountBean addAccount(AccountBean accountBean) throws Exception;

    //修改账号
    public void patchAccount(AccountBean accountBean) throws Exception;
}
