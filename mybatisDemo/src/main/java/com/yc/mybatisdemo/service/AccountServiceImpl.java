package com.yc.mybatisdemo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.mybatisdemo.domain.PageAccountDTO;
import com.yc.mybatisdemo.mapper.MyAccountMapper;
import com.yc.mybatisdemo.model.MyAccount;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author: josan_tang
 * @create_date: 2022/4/8 15:26
 * @desc:
 * @version:
 * 条件构造器 https://baomidou.com/pages/10c804/#abstractwrapper
 * https://www.hxstrive.com/subject/mybatis_plus.htm?id=304
 */
@Service
public class AccountServiceImpl extends ServiceImpl<MyAccountMapper, MyAccount> implements AccountService{

    @Override
    public MyAccount getAccountByUrid(String urid) {
         return this.getById(urid);
    }

    /**
     * 通过QueryWrapper查询数据
     * @param urid
     * @return
     */
    @Override
    public MyAccount getByLambdaQueryWrapper(String urid) {
        LambdaQueryWrapper<MyAccount> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(MyAccount::getId, urid);
        return this.getOne(lambdaQueryWrapper);
    }

    @Override
    public MyAccount getSpecificFidld(String urid) {
        LambdaQueryWrapper<MyAccount> lambdaQueryWrapper = Wrappers.lambdaQuery();
        //使用select选择字段如果加多次，以最后一次为准
        lambdaQueryWrapper.select(MyAccount::getAccount, MyAccount::getId);
        lambdaQueryWrapper.eq(MyAccount::getId, urid);
        return this.getOne(lambdaQueryWrapper);
    }

    /**
     * 通过UpdateWrapper更新数据
     * @param name
     * @return
     */
    @Override
    public boolean updateByLambdaUpdateWrapper(String urid, String name) {
        LambdaUpdateWrapper<MyAccount> lambdaQueryWrapper = Wrappers.lambdaUpdate();
        lambdaQueryWrapper.eq(MyAccount::getId, urid);
        lambdaQueryWrapper.set(MyAccount::getMerchantName, name);
        return this.update(lambdaQueryWrapper);
    }

    @Override
    public void testWrapper() {
        //AbstractWrapper
        //说明:
        //QueryWrapper(LambdaQueryWrapper) 和 UpdateWrapper(LambdaUpdateWrapper) 的父类
        //用于生成 sql 的 where 条件, entity 属性也用于生成 sql 的 where 条件
        //注意: entity 生成的 where 条件与 使用各个 api 生成的 where 条件没有任何关联行为

        //1.allEq
        //allEq(Map<R, V> params)
        //allEq(Map<R, V> params, boolean null2IsNull)
        //allEq(boolean condition, Map<R, V> params, boolean null2IsNull)
        LambdaUpdateWrapper<MyAccount> lambdaQueryWrapper = Wrappers.lambdaUpdate();
        Map<String, Object> params = new HashMap<>();
        params.put("id", "1");
        // todo
        //lambdaQueryWrapper.allEq(params);

        //eq  等于 =
        // 例  eq("name", "老王")--->name = '老王'
        LambdaUpdateWrapper<MyAccount> eqQueryWrapper = Wrappers.lambdaUpdate();
        eqQueryWrapper.eq(MyAccount::getId, "1");
        MyAccount eqMyAccount = this.getOne(eqQueryWrapper);
        System.out.println("eqMyAccount:"  + eqMyAccount);

        //ne 不等于 <>
        // 例 ne("name", "老王")--->name <> '老王'
        LambdaUpdateWrapper<MyAccount> neQueryWrapper = Wrappers.lambdaUpdate();
        neQueryWrapper.ne(MyAccount::getId, "1");
        List<MyAccount> neMyAccountList = this.list(neQueryWrapper);
        System.out.println("neMyAccount:"  + neMyAccountList);

        //gt  大于 >
        //例: gt("age", 18)--->age > 18

        //ge  大于等于 >=
        //例: ge("age", 18)--->age >= 18

        //lt 小于 <
        //例: lt("age", 18)--->age < 18

        //le 小于等于 <=
        //例: le("age", 18)--->age <= 18

        //between BETWEEN 值1 AND 值2
        //例: between("age", 18, 30)--->age between 18 and 30

        //notBetween NOT BETWEEN 值1 AND 值2
        //例: notBetween("age", 18, 30)--->age not between 18 and 30

        //like  LIKE '%值%'
        //例: like("name", "王")--->name like '%王%'

        //notLike NOT LIKE '%值%'
        //例: notLike("name", "王")--->name not like '%王%'

        //likeLeft LIKE '%值'
        //例: likeLeft("name", "王")--->name like '%王'

        //likeRight LIKE '值%'
        //例: likeRight("name", "王")--->name like '王%'

        //isNull  字段 IS NULL
        //例: isNull("name")--->name is null

        //isNotNull  字段 IS NOT NULL
        //例: isNotNull("name")--->name is not null

        //in  字段 IN (value.get(0), value.get(1), ...)
        //例: in("age",{1,2,3})--->age in (1,2,3)

        //notIn  字段 NOT IN (value.get(0), value.get(1), ...)
        //例: notIn("age",{1,2,3})--->age not in (1,2,3)

        //inSql  字段 IN ( sql语句 )
        //例: inSql("age", "1,2,3,4,5,6")--->age in (1,2,3,4,5,6)
        //例: inSql("id", "select id from table where id < 3")--->id in (select id from table where id < 3)

        //notInSql  字段 NOT IN ( sql语句 )
        //例: notInSql("age", "1,2,3,4,5,6")--->age not in (1,2,3,4,5,6)
        //例: notInSql("id", "select id from table where id < 3")--->id not in (select id from table where id < 3)

        //groupBy 分组：GROUP BY 字段, ...
        //例: groupBy("id", "name")--->group by id,name

        //orderByAsc 排序：ORDER BY 字段, ... ASC
        //例: orderByAsc("id", "name")--->order by id ASC,name ASC

        //orderByDesc 排序：ORDER BY 字段, ... DESC
        //例: orderByDesc("id", "name")--->order by id DESC,name DESC

        //todo orderBy 排序：ORDER BY 字段, ...
        //例: orderBy(true, true, "id", "name")--->order by id ASC,name ASC

        //having HAVING ( sql语句 )
        //例: having("sum(age) > 10")--->having sum(age) > 10
        //例: having("sum(age) > {0}", 11)--->having sum(age) > 11

        //todo func func 方法(主要方便在出现if...else下调用不同方法能不断链)
        //例: func(i -> if(true) {i.eq("id", 1)} else {i.ne("id", 1)})

        //or 拼接 OR
        //主动调用or表示紧接着下一个方法不是用and连接!(不调用or则默认为使用and连接)
        //例: eq("id",1).or().eq("name","老王")--->id = 1 or name = '老王'
        //OR 嵌套
        //or(i -> i.eq("name", "李白").ne("status", "活着"))--->or (name = '李白' and status <> '活着')
        LambdaUpdateWrapper<MyAccount> orLambdaQueryWrapper1 = Wrappers.lambdaUpdate();
        orLambdaQueryWrapper1.eq(MyAccount::getId, "1").or().eq(MyAccount::getId, "2");
        List<MyAccount> orAccountList1 = this.list(orLambdaQueryWrapper1);
        System.out.println("orAccountList1:" + orAccountList1);

        //相当于 ： SELECT id,merchant_name,account,password,created_time,updated_time,del_flag FROM my_account WHERE (del_flag = ? AND id = ? OR id = ?)
        //最后的条件是（del_flag = ? AND id = ?） OR id = ?
        LambdaUpdateWrapper<MyAccount> orLambdaQueryWrapper2 = Wrappers.lambdaUpdate();
        orLambdaQueryWrapper2.eq(MyAccount::getDelFlag, 1);
        orLambdaQueryWrapper2.eq(MyAccount::getId, "1").or().eq(MyAccount::getId, "2");
        List<MyAccount> orAccountList2 = this.list(orLambdaQueryWrapper2);
        System.out.println("orAccountList2:" + orAccountList2);

        //SELECT id,merchant_name,account,password,created_time,updated_time,del_flag FROM my_account WHERE (del_flag = ? AND (id = ? OR id = ?))
        //使用and才是我们想要的(del_flag = ? AND (id = ? OR id = ?))
        LambdaUpdateWrapper<MyAccount> orLambdaQueryWrapper3 = Wrappers.lambdaUpdate();
        orLambdaQueryWrapper3.eq(MyAccount::getDelFlag, 1);
        orLambdaQueryWrapper3.and(wrapper -> wrapper.eq(MyAccount::getId, "1").or().eq(MyAccount::getId, "2"));
        List<MyAccount> orAccountList3 = this.list(orLambdaQueryWrapper3);
        System.out.println("orAccountList3:" + orAccountList3);

        //and AND 嵌套
        //例: and(i -> i.eq("name", "李白").ne("status", "活着"))--->and (name = '李白' and status <> '活着')

        //nested 正常嵌套 不带 AND 或者 OR
        //例: nested(i -> i.eq("name", "李白").ne("status", "活着"))--->(name = '李白' and status <> '活着')

        //apply 拼接 sql
        //该方法可用于数据库函数 动态入参的params对应前面applySql内部的{index}部分.这样是不会有sql注入风险的,反之会有!
        //例: apply("id = 1")--->id = 1
        //例: apply("date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'")--->date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'")
        //例: apply("date_format(dateColumn,'%Y-%m-%d') = {0}", "2008-08-08")--->date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'")

        //last  无视优化规则直接拼接到 sql 的最后
        //注意事项:只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用
        //例: last("limit 1")

        //exists 拼接 EXISTS ( sql语句 )
        //例: exists("select id from table where age = 1")--->exists (select id from table where age = 1)

        //notExists 拼接 NOT EXISTS ( sql语句 )
        //例: notExists("select id from table where age = 1")--->not exists (select id from table where age = 1)

        //QueryWrapper
        //说明:
        //继承自 AbstractWrapper ,自身的内部属性 entity 也用于生成 where 条件
        //及 LambdaQueryWrapper, 可以通过 new QueryWrapper().lambda() 方法获取

        //select 设置查询字段
        //说明:
        //以上方法分为两类.
        //第二类方法为:过滤查询字段(主键除外),入参不包含 class 的调用前需要wrapper内的entity属性有值! 这两类方法重复调用以最后一次为准
        //例: select("id", "name", "age")
        //例: select(i -> i.getProperty().startsWith("test"))

        //distinct需要使用select
        LambdaQueryWrapper<MyAccount> distinctQueryWrapper = new QueryWrapper<MyAccount>().select("distinct merchant_name").lambda();
        distinctQueryWrapper.eq(MyAccount::getPassword, "Abc123456");
        int distinctAccount = this.count(distinctQueryWrapper);
        System.out.println("distinctAccount:" + distinctAccount);

        //UpdateWrapper
        //说明:
        //继承自 AbstractWrapper ,自身的内部属性 entity 也用于生成 where 条件
        //及 LambdaUpdateWrapper, 可以通过 new UpdateWrapper().lambda() 方法获取!

        //set SQL SET 字段
        //例: set("name", "老李头")
        //例: set("name", "")--->数据库字段值变为空字符串
        //例: set("name", null)--->数据库字段值变为null

        //setSql 设置 SET 部分 SQL
        //例: setSql("name = '老李头'")

        //lambda  获取 LambdaWrapper
        //在QueryWrapper中是获取LambdaQueryWrapper
        //在UpdateWrapper中是获取LambdaUpdateWrapper

        //链式调用 lambda 式
        // 区分:
        // 链式调用 普通
        //UpdateChainWrapper<T> update();
        // 链式调用 lambda 式。注意：不支持 Kotlin
        //LambdaUpdateChainWrapper<T> lambdaUpdate();

        // 等价示例：
        //query().eq("id", value).one();
        //lambdaQuery().eq(Entity::getId, value).one();

        // 等价示例：
        //update().eq("id", value).remove();
        //lambdaUpdate().eq(Entity::getId, value).remove();
    }

    @Override
    public Page<MyAccount> selectPage(PageAccountDTO dto) {
        Page<MyAccount> page = null;
        if (dto.getPage() == null || dto.getLimit() == null) {
            page = new Page<>(1, 10);
        } else {
            page = new Page<>(dto.getPage(), dto.getLimit());
        }
        LambdaQueryWrapper<MyAccount> myAccountLambdaQueryWrapper = Wrappers.lambdaQuery();
        myAccountLambdaQueryWrapper.eq(MyAccount::getDelFlag, 1);
        if (StringUtils.isNotBlank(dto.getId())) {
            myAccountLambdaQueryWrapper.eq(MyAccount::getId, dto.getId());
        }
        page = this.page(page, myAccountLambdaQueryWrapper);
        return page;
    }

    @Override
    public void insert() {
        List<MyAccount> myAccounts = new ArrayList<>();
        MyAccount myAccount = new MyAccount();
        myAccount.setId("1000");
        myAccounts.add(myAccount);
        this.saveBatch(myAccounts);
    }

    @Override
    public void testSimpleMappper() {

/*        MyAccount myAccount = myAccountMapper.selectByPrimaryKey("1");
        myAccount.setMerchantName(null);
        myAccount.setUpdatedTime(new Date());
        //为null的值不会更新
        myAccountMapper.updateById(myAccount);

        MyAccount myAccount2 = myAccountMapper.selectByPrimaryKey("2");
        myAccount2.setMerchantName("2");
        myAccount2.setUpdatedTime(new Date());
        myAccountMapper.updateById(myAccount2);*/

        MyAccount account1 = this.getById("3");
        account1.setMerchantName("33");
        LambdaUpdateWrapper<MyAccount> lambdaQueryWrapper = Wrappers.lambdaUpdate();
        lambdaQueryWrapper.set(MyAccount::getAccount, null);
        this.update(account1, lambdaQueryWrapper);
    }

    @Override
    public void distinct() {
        QueryWrapper<MyAccount> lambdaQueryWrapper = new QueryWrapper();
        lambdaQueryWrapper.select("distinct merchant_name");
        List<MyAccount> myAccountList = this.list(lambdaQueryWrapper);
    }

}
