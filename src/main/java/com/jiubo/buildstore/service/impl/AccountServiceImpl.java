package com.jiubo.buildstore.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.AccountBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.dao.AccountDao;
import com.jiubo.buildstore.service.AccountService;
import com.jiubo.buildstore.util.CookieTools;
import com.jiubo.buildstore.util.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mwl
 * @since 2020-02-10
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Value("${tokenLife}")
    private int tokenLife;

    @Value("${accountLife}")
    private int accountLife;

    @Autowired
    private AccountDao accountDao;


    @Override
    public List<AccountBean> queryAccountList(AccountBean accountBean) throws Exception {
        return accountDao.queryAccountList(accountBean);
    }

    @Override

    public JSONObject login(AccountBean accountBean) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isBlank(accountBean.getAccount())) throw new MessageException("账号不能为空!");
        if (StringUtils.isBlank(accountBean.getPwd())) throw new MessageException("密码不能为空!");
        String pwd = MD5Util.md5Encrypt32Lower(accountBean.getPwd());
        System.out.println("加密：" + pwd);
        accountBean.setPwd(pwd);
        List<AccountBean> accountBeans = queryAccountList(accountBean);
        AccountBean bean = null;
        if (accountBeans.isEmpty()) {
            throw new MessageException("账号或密码错误!");
        } else {
            bean = accountBeans.get(0);
            bean.setPwd("");

            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            String accessToken = URLEncoder.encode(bean.getAccount().concat("604800"), Constant.Charset.UTF8);
            jsonObject.put("accessToken", accessToken);
            jsonObject.put("accountData", bean);

            CookieTools.addCookie(response, "accessToken", accessToken, tokenLife);
            CookieTools.addCookie(response, "accountData", URLEncoder.encode(JSON.toJSONString(bean), Constant.Charset.UTF8), accountLife);
        }
        return jsonObject;
    }


    public AccountBean addAccount(AccountBean accountBean) throws Exception {
        List<AccountBean> accountBeans = queryAccountList(accountBean);
        if (null != accountBeans && accountBeans.size() > 0 ) throw new MessageException("该账号已存在");
        accountDao.addAccount(accountBean);

        return accountBean;
    }

    @Override
    public void patchAccount(AccountBean accountBean) throws Exception {
        List<AccountBean> accountBeans = queryAccountList(new AccountBean().setAccount(accountBean.getAccount()));
        if (null != accountBeans && accountBeans.size() > 0 ) throw new MessageException("该账号已存在");
//        List<AccountBean> accountBeanList = queryAccountList(new AccountBean().setPwd(accountBean.getPwd()));
//        if (null == accountBeanList || accountBeanList.size() == 0 ) throw new MessageException("原密码输入不正确");
        accountDao.patchAccount(accountBean);
    }
    
}
