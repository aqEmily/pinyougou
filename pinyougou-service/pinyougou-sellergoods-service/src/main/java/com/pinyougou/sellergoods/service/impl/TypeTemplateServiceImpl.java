package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.mapper.TypeTemplateMapper;
import com.pinyougou.pojo.TypeTemplate;
import com.pinyougou.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service(interfaceName = "com.pinyougou.service.TypeTemplateService" )
@Transactional
public class TypeTemplateServiceImpl implements TypeTemplateService {
    /** 注入数据访问接口代理对象 */

    @Autowired
    private TypeTemplateMapper typeTemplateMapper;

    @Autowired
    private BrandMapper brandMapper;


    /**
     * 添加类型模板
     */
    @Override
    public void save(TypeTemplate typeTemplate) {
        typeTemplateMapper.insertSelective(typeTemplate);
    }


    /** 修改类型模板 */
    @Override
    public void update(TypeTemplate typeTemplate) {
        typeTemplateMapper.updateByPrimaryKeySelective(typeTemplate);
    }


    @Override
    public void delete(Serializable id) {

    }

    /** 删除类型模板 */
    @Override
    public void deleteAll(Serializable[] ids) {
        // 创建示范对象
        Example example = new Example(TypeTemplate.class);
        // 创建条件对象
        Example.Criteria criteria = example.createCriteria();
        // 添加in条件
        criteria.andIn("id", Arrays.asList(ids));
        // 条件删除
        typeTemplateMapper.deleteByExample(example);
    }

    @Override
    public TypeTemplate findOne(Serializable id) {
        return null;
    }

    @Override
    public List<TypeTemplate> findAll() {
        return null;
    }
    /** 分页查询类型模版 */
    @Override
    public PageResult findByPage(TypeTemplate typeTemplate, int page, int rows) {
        try{
            /** 开始分页 */
            PageInfo<TypeTemplate> pageInfo = PageHelper.startPage(page, rows)
                    .doSelectPageInfo( new ISelect() {
                        @Override
                        public void doSelect() {
                            typeTemplateMapper.findAll(typeTemplate);
                        }
                    });
            return new PageResult(pageInfo.getTotal(), pageInfo.getList());
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    /** 查询所有的品牌(id与name) */
    public List<Map<String, Object>> findAllByIdAndName(){
        try{
            return brandMapper.findAllByIdAndName();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }



}