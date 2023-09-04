package com.shopping.goods.cotroller;

import api.R;
import com.alibaba.druid.util.Base64;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.shopping.goods.service.SkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.shopping.goods.pojo.dto.SkuSaveDTO;
import com.shopping.goods.pojo.entity.SkuEntity;
import springfox.documentation.annotations.ApiIgnore;
import utils.DBConstant;
import utils.PageUtils;
import utils.ValidatorUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * 商品表
 *
 * @author dhl
 * @email dinghl@sqqmall.com
 * @date 2020-11-23 15:45:56
 */
@RestController
@CrossOrigin
@RequestMapping("goods/sku")
@Api(value = "", description = "sku业务相关接口", tags = {"sku业务相关接口"})
public class SkuController {
    @Autowired
    private SkuService skuService;

    @Value("${server.port}")
    private String port;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "sku列表查询", notes = "sku列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = DBConstant.PAGE, value = "当前页", required = true, dataType = "int"),
            @ApiImplicitParam(name = DBConstant.LIMIT, value = "显示条目", required = true, dataType = "int"),
    })
    public R list(@ApiIgnore @RequestBody(required = false) Map<String, Object> params) {
        PageUtils page = skuService.queryPage(params);
        String resultJson = JSON.toJSONString(page.getList());
//        List<?> newList = page.getList().stream/**/().forEach(e -> ).collect(Collectors.toList());

        String encodeStr = Base64.byteArrayToAltBase64(resultJson.getBytes(StandardCharsets.UTF_8));
//        System.out.println(encodeStr);
//        System.out.println(org.apache.commons.codec.binary.Base64.decodeBase64(encodeStr));
//        System.out.println(encodeStr);
//        System.out.println(Base64.getDecoder().decode(encodeStr).toString());
        return R.data(encodeStr);
    }

    public static void main(String[] args) {
        String str = "{\"code\":200,\"success\":true,\"data\":\"[{\\\"alertNum\\\":9,\\\"brandName\\\":\\\"TCL\\\",\\\"categoryId\\\":76,\\\"categoryName\\\":\\\"平板电视\\\",\\\"commentNum\\\":2457,\\\"createTime\\\":1562653437000,\\\"id\\\":1148477873175142400,\\\"image\\\":\\\"http://img10.360buyimg.com/n7/jfs/t27247/67/2175778313/348661/d5306dd8/5bf86dcaN20a58cd6.jpg\\\",\\\"images\\\":\\\"http://img13.360buyimg.com/n7/jfs/t1/47562/33/125/375477/5cd2c3bdE4164c44c/7c809fa28037ad16.jpg,http://img14.360buyimg.com/n7/jfs/t1/75428/4/2831/201268/5d11cae4E52e6be64/2558ad6adbcc8d07.jpg,http://img11.360buyimg.com/n7/jfs/t26053/226/1746531202/221888/c26bda62/5bbc08c0N2a24122f.jpg,http://img14.360buyimg.com/n7/jfs/t29521/271/1222017042/96947/4ad81a88/5cda6c43N1fc45474.jpg\\\",\\\"name\\\":\\\"TCL 测试掀暮涩  立体声  20英寸  165\\\",\\\"num\\\":116,\\\"price\\\":746,\\\"saleNum\\\":64,\\\"sn\\\":\\\"7a3c17b420934ddd965fd17974ff9534\\\",\\\"spec\\\":\\\"{\\\\\\\"电视音响效果\\\\\\\":\\\\\\\"立体声\\\\\\\",\\\\\\\"电视屏幕尺寸\\\\\\\":\\\\\\\"20英寸\\\\\\\",\\\\\\\"尺码\\\\\\\":\\\\\\\"165\\\\\\\"}\\\",\\\"spuId\\\":1148477873158365184,\\\"status\\\":\\\"1\\\",\\\"updateTime\\\":1562653437000,\\\"weight\\\":4171},{\\\"alertNum\\\":6,\\\"brandName\\\":\\\"TCL\\\",\\\"categoryId\\\":76,\\\"categoryName\\\":\\\"平板电视\\\",\\\"commentNum\\\":4743,\\\"createTime\\\":1562653437000,\\\"id\\\":1148477873196113920,\\\"image\\\":\\\"http://img12.360buyimg.com/n7/jfs/t1/2186/34/688/145171/5b9246e5Ee83b63cd/bd6f4345da3ddc9f.jpg\\\",\\\"images\\\":\\\"http://img13.360buyimg.com/n7/jfs/t1/47562/33/125/375477/5cd2c3bdE4164c44c/7c809fa28037ad16.jpg,http://img11.360buyimg.com/n7/jfs/t1/21612/38/4767/334593/5c3582e4E8fd541ce/a17353d206e19e68.jpg,http://img11.360buyimg.com/n7/jfs/t1/6413/35/485/159824/5bc9349eE2ddb3c30/6dddf7ef5a4de686.jpg,http://img11.360buyimg.com/n7/jfs/t1/55838/29/2889/124054/5d0b36d7E9324d58e/aec1138207c5157f.jpg\\\",\\\"name\\\":\\\"TCL 测试掀暮涩  环绕  20英寸  165\\\",\\\"num\\\":205,\\\"price\\\":528,\\\"saleNum\\\":96,\\\"sn\\\":\\\"eea11988363849e48bec205b18d9d780\\\",\\\"spec\\\":\\\"{\\\\\\\"电视音响效果\\\\\\\":\\\\\\\"环绕\\\\\\\",\\\\\\\"电视屏幕尺寸\\\\\\\":\\\\\\\"20英寸\\\\\\\",\\\\\\\"尺码\\\\\\\":\\\\\\\"165\\\\\\\"}\\\",\\\"spuId\\\":1148477873158365184,\\\"status\\\":\\\"1\\\",\\\"updateTime\\\":1562653437000,\\\"weight\\\":6357},{\\\"alertNum\\\":9,\\\"brandName\\\":\\\"TCL\\\",\\\"categoryId\\\":76,\\\"categoryName\\\":\\\"平板电视\\\",\\\"commentNum\\\":6006,\\\"createTime\\\":1562653437000,\\\"id\\\":1148477873208696832,\\\"image\\\":\\\"http://img10.360buyimg.com/n8/jfs/t1/71721/34/3065/124817/5d147ac3E049e04af/957ae2bddb710acd.jpg\\\",\\\"images\\\":\\\"http://img13.360buyimg.com/n7/jfs/t1/47562/33/125/375477/5cd2c3bdE4164c44c/7c809fa28037ad16.jpg,http://img10.360buyimg.com/n8/jfs/t1/59268/34/3382/122779/5d1349eaEa1ff3003/0f26e3a1f142ba00.jpg,http://img13.360buyimg.com/n7/jfs/t1/47562/33/125/375477/5cd2c3bdE4164c44c/7c809fa28037ad16.jpg,http://img13.360buyimg.com/n7/jfs/t1/39124/14/10388/134846/5d1b2496E9e9b5b7c/374d96244d788873.jpg\\\",\\\"name\\\":\\\"TCL 测试掀暮涩  小影院  20英寸  165\\\",\\\"num\\\":202,\\\"price\\\":11,\\\"saleNum\\\":81,\\\"sn\\\":\\\"0608799f81c74c1aa0c6eaae42fd802a\\\",\\\"spec\\\":\\\"{\\\\\\\"电视音响效果\\\\\\\":\\\\\\\"小影院\\\\\\\",\\\\\\\"电视屏幕尺寸\\\\\\\":\\\\\\\"20英寸\\\\\\\",\\\\\\\"尺码\\\\\\\":\\\\\\\"165\\\\\\\"}\\\",\\\"spuId\\\":1148477873158365184,\\\"status\\\":\\\"1\\\",\\\"updateTime\\\":1562653437000,\\\"weight\\\":1857},{\\\"alertNum\\\":5,\\\"brandName\\\":\\\"TCL\\\",\\\"categoryId\\\":76,\\\"categoryName\\\":\\\"平板电视\\\",\\\"commentNum\\\":7441,\\\"createTime\\\":1562653437000,\\\"id\\\":1148477873221279744,\\\"image\\\":\\\"http://img10.360buyimg.com/n7/jfs/t1/42968/36/6334/124909/5cff4240Ebf980183/d7cc572e2e8ee29b.jpg\\\",\\\"images\\\":\\\"http://img13.360buyimg.com/n7/jfs/t1/47562/33/125/375477/5cd2c3bdE4164c44c/7c809fa28037ad16.jpg,http://img13.360buyimg.com/n7/jfs/t1/39124/14/10388/134846/5d1b2496E9e9b5b7c/374d96244d788873.jpg,http://img12.360buyimg.com/n7/jfs/t1/10979/12/198/438043/5bd9a7e0Efb7369a8/8b6fda6a4a7d78b0.jpg,http://img10.360buyimg.com/n7/jfs/t1/4522/8/12858/225377/5bd52796E0ead8fc5/9a3c6379a353aafe.jpg\\\",\\\"name\\\":\\\"TCL 测试掀暮涩  立体声  50英寸  165\\\",\\\"num\\\":204,\\\"price\\\":957,\\\"saleNum\\\":96,\\\"sn\\\":\\\"f924c40c1b924d9588dd702c4b392cd2\\\",\\\"spec\\\":\\\"{\\\\\\\"电视音响效果\\\\\\\":\\\\\\\"立体声\\\\\\\",\\\\\\\"电视屏幕尺寸\\\\\\\":\\\\\\\"50英寸\\\\\\\",\\\\\\\"尺码\\\\\\\":\\\\\\\"165\\\\\\\"}\\\",\\\"spuId\\\":1148477873158365184,\\\"status\\\":\\\"1\\\",\\\"updateTime\\\":1562653437000,\\\"weight\\\":2439},{\\\"alertNum\\\":0,\\\"brandName\\\":\\\"TCL\\\",\\\"categoryId\\\":76,\\\"categoryName\\\":\\\"平板电视\\\",\\\"commentNum\\\":8145,\\\"createTime\\\":1562653437000,\\\"id\\\":1148477873229668352,\\\"image\\\":\\\"http://img10.360buyimg.com/n7/jfs/t1/31203/23/9994/197564/5cab210dE64e20358/d94e70571c1fd455.jpg\\\",\\\"images\\\":\\\"http://img13.360buyimg.com/n7/jfs/t1/47562/33/125/375477/5cd2c3bdE4164c44c/7c809fa28037ad16.jpg,http://img14.360buyimg.com/n7/jfs/t1/79384/25/1731/122984/5d00e6a7E5003ad8c/624ceba06465d39d.jpg,http://img12.360buyimg.com/n7/jfs/t1/15377/32/3914/166365/5c2cd4daEf226137b/d0a8008802ea0b5d.jpg,http://img11.360buyimg.com/n8/jfs/t1/49987/17/1591/501668/5cf51d80Eefce08ad/04d35685730f250d.jpg\\\",\\\"name\\\":\\\"TCL 测试掀暮涩  环绕  50英寸  165\\\",\\\"num\\\":207,\\\"price\\\":574,\\\"saleNum\\\":0,\\\"sn\\\":\\\"a7675a817dd54e98b0d24d2936590567\\\",\\\"spec\\\":\\\"{\\\\\\\"电视音响效果\\\\\\\":\\\\\\\"环绕\\\\\\\",\\\\\\\"电视屏幕尺寸\\\\\\\":\\\\\\\"50英寸\\\\\\\",\\\\\\\"尺码\\\\\\\":\\\\\\\"165\\\\\\\"}\\\",\\\"spuId\\\":1148477873158365184,\\\"status\\\":\\\"1\\\",\\\"updateTime\\\":1562653437000,\\\"weight\\\":7559},{\\\"alertNum\\\":3,\\\"brandName\\\":\\\"TCL\\\",\\\"categoryId\\\":76,\\\"categoryName\\\":\\\"平板电视\\\",\\\"commentNum\\\":717,\\\"createTime\\\":1562653437000,\\\"id\\\":1148477873242251264,\\\"image\\\":\\\"http://img12.360buyimg.com/n7/jfs/t1/66064/32/3063/175413/5d1472e1E88469144/8b4a92696353c6ef.jpg\\\",\\\"images\\\":\\\"http://img13.360buyimg.com/n7/jfs/t1/47562/33/125/375477/5cd2c3bdE4164c44c/7c809fa28037ad16.jpg,http://img14.360buyimg.com/n7/jfs/t1/44213/35/7547/226060/5d11713fE4444a8ca/202f49987ad41a5e.jpg,http://img14.360buyimg.com/n7/jfs/t29521/271/1222017042/96947/4ad81a88/5cda6c43N1fc45474.jpg,http://img11.360buyimg.com/n7/jfs/t1/6350/31/9335/346484/5c136a1dE32b7b86b/cfaa654ebfa098db.jpg\\\",\\\"name\\\":\\\"TCL 测试掀暮涩  小影院  50英寸  165\\\",\\\"num\\\":204,\\\"price\\\":244,\\\"saleNum\\\":19,\\\"sn\\\":\\\"e367ed92339749c4b9d7e9cafd80de2c\\\",\\\"spec\\\":\\\"{\\\\\\\"电视音响效果\\\\\\\":\\\\\\\"小影院\\\\\\\",\\\\\\\"电视屏幕尺寸\\\\\\\":\\\\\\\"50英寸\\\\\\\",\\\\\\\"尺码\\\\\\\":\\\\\\\"165\\\\\\\"}\\\",\\\"spuId\\\":1148477873158365184,\\\"status\\\":\\\"1\\\",\\\"updateTime\\\":1562653437000,\\\"weight\\\":6025},{\\\"alertNum\\\":3,\\\"brandName\\\":\\\"TCL\\\",\\\"categoryId\\\":76,\\\"categoryName\\\":\\\"平板电视\\\",\\\"commentNum\\\":9908,\\\"createTime\\\":1562653437000,\\\"id\\\":1148477873254834176,\\\"image\\\":\\\"http://img12.360buyimg.com/n7/jfs/t1/2186/34/688/145171/5b9246e5Ee83b63cd/bd6f4345da3ddc9f.jpg\\\",\\\"images\\\":\\\"http://img13.360buyimg.com/n7/jfs/t1/47562/33/125/375477/5cd2c3bdE4164c44c/7c809fa28037ad16.jpg,http://img12.360buyimg.com/n7/jfs/t1/15377/32/3914/166365/5c2cd4daEf226137b/d0a8008802ea0b5d.jpg,http://img12.360buyimg.com/n7/jfs/t20515/322/244144345/155580/43d75b84/5b061d39N63a954dd.jpg,http://img11.360buyimg.com/n8/jfs/t1/49987/17/1591/501668/5cf51d80Eefce08ad/04d35685730f250d.jpg\\\",\\\"name\\\":\\\"TCL 测试掀暮涩  立体声  60英寸  165\\\",\\\"num\\\":205,\\\"price\\\":284,\\\"saleNum\\\":24,\\\"sn\\\":\\\"bb94ebdcc1d346b7b6e5b97d778fc2c1\\\",\\\"spec\\\":\\\"{\\\\\\\"电视音响效果\\\\\\\":\\\\\\\"立体声\\\\\\\",\\\\\\\"电视屏幕尺寸\\\\\\\":\\\\\\\"60英寸\\\\\\\",\\\\\\\"尺码\\\\\\\":\\\\\\\"165\\\\\\\"}\\\",\\\"spuId\\\":1148477873158365184,\\\"status\\\":\\\"1\\\",\\\"updateTime\\\":1562653437000,\\\"weight\\\":9930},{\\\"alertNum\\\":7,\\\"brandName\\\":\\\"TCL\\\",\\\"categoryId\\\":76,\\\"categoryName\\\":\\\"平板电视\\\",\\\"commentNum\\\":3334,\\\"createTime\\\":1562653437000,\\\"id\\\":1148477873267417088,\\\"image\\\":\\\"http://img10.360buyimg.com/n7/jfs/t1/44237/19/7294/57280/5d0c8456E294049e1/27a45daf4b869fb2.jpg\\\",\\\"images\\\":\\\"http://img13.360buyimg.com/n7/jfs/t1/47562/33/125/375477/5cd2c3bdE4164c44c/7c809fa28037ad16.jpg,http://img11.360buyimg.com/n8/jfs/t1/81769/22/792/465850/5cf013e3Eaefcdda5/5b880b61ef1ad5a0.jpg,http://img11.360buyimg.com/n7/jfs/t1/21612/38/4767/334593/5c3582e4E8fd541ce/a17353d206e19e68.jpg,http://img13.360buyimg.com/n7/jfs/t1/47562/33/125/375477/5cd2c3bdE4164c44c/7c809fa28037ad16.jpg\\\",\\\"name\\\":\\\"TCL 测试掀暮涩  环绕  60英寸  165\\\",\\\"num\\\":205,\\\"price\\\":477,\\\"saleNum\\\":4,\\\"sn\\\":\\\"036ed2f7cfec446fac9f27e9c9b15cfd\\\",\\\"spec\\\":\\\"{\\\\\\\"电视音响效果\\\\\\\":\\\\\\\"环绕\\\\\\\",\\\\\\\"电视屏幕尺寸\\\\\\\":\\\\\\\"60英寸\\\\\\\",\\\\\\\"尺码\\\\\\\":\\\\\\\"165\\\\\\\"}\\\",\\\"spuId\\\":1148477873158365184,\\\"status\\\":\\\"1\\\",\\\"updateTime\\\":1562653437000,\\\"weight\\\":2007},{\\\"alertNum\\\":1,\\\"brandName\\\":\\\"TCL\\\",\\\"categoryId\\\":76,\\\"categoryName\\\":\\\"平板电视\\\",\\\"commentNum\\\":5130,\\\"createTime\\\":1562653437000,\\\"id\\\":1148477873280000000,\\\"image\\\":\\\"http://img11.360buyimg.com/n7/jfs/t10480/167/2719213601/153778/5243bb25/5cd54bbeN68b1054d.jpg\\\",\\\"images\\\":\\\"http://img13.360buyimg.com/n7/jfs/t1/47562/33/125/375477/5cd2c3bdE4164c44c/7c809fa28037ad16.jpg,http://img11.360buyimg.com/n8/jfs/t1/81769/22/792/465850/5cf013e3Eaefcdda5/5b880b61ef1ad5a0.jpg,http://img12.360buyimg.com/n7/jfs/t1/2186/34/688/145171/5b9246e5Ee83b63cd/bd6f4345da3ddc9f.jpg,http://img10.360buyimg.com/n8/jfs/t1/54147/15/3677/111457/5d16371bE9dcb31e7/b2e412c574c7d848.jpg\\\",\\\"name\\\":\\\"TCL 测试掀暮涩  小影院  60英寸  165\\\",\\\"num\\\":209,\\\"price\\\":796,\\\"saleNum\\\":17,\\\"sn\\\":\\\"18ded60166cf43429547f4370b8bf78c\\\",\\\"spec\\\":\\\"{\\\\\\\"电视音响效果\\\\\\\":\\\\\\\"小影院\\\\\\\",\\\\\\\"电视屏幕尺寸\\\\\\\":\\\\\\\"60英寸\\\\\\\",\\\\\\\"尺码\\\\\\\":\\\\\\\"165\\\\\\\"}\\\",\\\"spuId\\\":1148477873158365184,\\\"status\\\":\\\"1\\\",\\\"updateTime\\\":1562653437000,\\\"weight\\\":6565},{\\\"alertNum\\\":6,\\\"brandName\\\":\\\"TCL\\\",\\\"categoryId\\\":76,\\\"categoryName\\\":\\\"平板电视\\\",\\\"commentNum\\\":4537,\\\"createTime\\\":1562653437000,\\\"id\\\":1148477873288388608,\\\"image\\\":\\\"http://img12.360buyimg.com/n7/jfs/t1/10979/12/198/438043/5bd9a7e0Efb7369a8/8b6fda6a4a7d78b0.jpg\\\",\\\"images\\\":\\\"http://img13.360buyimg.com/n7/jfs/t1/47562/33/125/375477/5cd2c3bdE4164c44c/7c809fa28037ad16.jpg,http://img11.360buyimg.com/n8/jfs/t1/49987/17/1591/501668/5cf51d80Eefce08ad/04d35685730f250d.jpg,http://img12.360buyimg.com/n7/jfs/t1/66272/26/3615/276256/5d1d6156Efc7119af/bee424949d1c1f9f.jpg,http://img11.360buyimg.com/n7/jfs/t1/6350/31/9335/346484/5c136a1dE32b7b86b/cfaa654ebfa098db.jpg\\\",\\\"name\\\":\\\"TCL 测试掀暮涩  立体声  20英寸  170\\\",\\\"num\\\":202,\\\"price\\\":906,\\\"saleNum\\\":21,\\\"sn\\\":\\\"a75c452df8544a0dba31f9f8fa938582\\\",\\\"spec\\\":\\\"{\\\\\\\"电视音响效果\\\\\\\":\\\\\\\"立体声\\\\\\\",\\\\\\\"电视屏幕尺寸\\\\\\\":\\\\\\\"20英寸\\\\\\\",\\\\\\\"尺码\\\\\\\":\\\\\\\"170\\\\\\\"}\\\",\\\"spuId\\\":1148477873158365184,\\\"status\\\":\\\"1\\\",\\\"updateTime\\\":1562653437000,\\\"weight\\\":9785},{\\\"alertNum\\\":7,\\\"brandName\\\":\\\"TCL\\\",\\\"categoryId\\\":76,\\\"categoryName\\\":\\\"平板电视\\\",\\\"commentNum\\\":3384,\\\"createTime\\\":1562653437000,\\\"id\\\":1148477873300971520,\\\"image\\\":\\\"http://img10.360buyimg.com/n7/jfs/t1/42968/36/6334/124909/5cff4240Ebf980183/d7cc572e2e8ee29b.jpg\\\",\\\"images\\\":\\\"http://img13.360buyimg.com/n7/jfs/t1/47562/33/125/375477/5cd2c3bdE4164c44c/7c809fa28037ad16.jpg,http://img14.360buyimg.com/n8/jfs/t1/33227/20/10569/196375/5ccef0bdEaaf9ee64/5996f1d6b44c6d2d.jpg,http://img11.360buyimg.com/n7/jfs/t1/21612/38/4767/334593/5c3582e4E8fd541ce/a17353d206e19e68.jpg,http://img12.360buyimg.com/n7/jfs/t1/64811/3/1181/238169/5cf76eedE4cede79a/9610f77876500f92.jpg\\\",\\\"name\\\":\\\"TCL 测试掀暮涩  环绕  20英寸  170\\\",\\\"num\\\":202,";

        String encodeStr = org.apache.commons.codec.binary.Base64.encodeBase64String(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(encodeStr);
        System.out.println(org.apache.commons.codec.binary.Base64.decodeBase64(encodeStr));
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "sku信息", notes = "sku信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value ="sku id", required = true, dataType = "Long", paramType = "path")
//    })
    public R info(@PathVariable("id") Long id) {
        SkuEntity sku = skuService.getById(id);

        return R.data(sku);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody SkuSaveDTO sku) {
        ValidatorUtils.validateEntity(sku);
        SkuEntity entity = new SkuEntity();
        BeanUtils.copyProperties(sku, entity);
        skuService.save(entity);
//        int i = 2/0 ;
        return R.success("ok");
    }

    /**
     * 保存
     */
    @PostMapping("/testSave")
    public R testSave() {
        SkuEntity skuSaveDTO = new SkuEntity();
        skuSaveDTO.setSn("test" + new Random().nextInt(10));
        skuSaveDTO.setName("testName" + new Random().nextInt(10));
        skuSaveDTO.setCommentNum(new Random().nextInt(3));
        skuSaveDTO.setAlertNum(new Random().nextInt(3));
        skuSaveDTO.setBrandName("testBrandName" + new Random().nextInt(10));
        skuSaveDTO.setCategoryId(1);
        skuSaveDTO.setCategoryName("testCategoryName" + new Random().nextInt(10));
        skuSaveDTO.setPrice(new Random().nextInt(3));
        skuSaveDTO.setStatus("1");
        skuSaveDTO.setSaleNum(0);
        skuSaveDTO.setNum(20);
        skuSaveDTO.setImage("sdfsdf");

        skuSaveDTO.setId(IdWorker.getId());
        skuService.save(skuSaveDTO);
//        int i = 2/0 ;
        return R.success("ok");
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody SkuEntity sku) {
        ValidatorUtils.validateEntity(sku);
        skuService.updateById(sku);

        return R.success("ok");
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        skuService.removeByIds(Arrays.asList(ids));

        return R.success("ok");
    }

}
