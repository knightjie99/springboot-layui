package com.wujie.springbootlayui.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.entity.AttendStaff;
import com.wujie.springbootlayui.bus.entity.GateAuthor;
import com.wujie.springbootlayui.bus.entity.Staff;
import com.wujie.springbootlayui.bus.service.IAttendStaffService;
import com.wujie.springbootlayui.bus.service.IGateAuthorService;
import com.wujie.springbootlayui.bus.service.IStaffCardService;
import com.wujie.springbootlayui.bus.service.IStaffService;
import com.wujie.springbootlayui.sys.common.*;
import com.wujie.springbootlayui.sys.entity.DTree;
import com.wujie.springbootlayui.sys.entity.Dept;
import com.wujie.springbootlayui.sys.service.IDeptService;
import com.wujie.springbootlayui.sys.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @Autowired
    private IStaffService staffService;

    @Autowired
    private IGateAuthorService authorService;

    @Autowired
    private IAttendStaffService attendStaffService;

    @Autowired
    private IStaffCardService cardService;

    /**
     * 加载部门左边的菜单树
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("loadDeptManagerLeftTreeJson")
    public DataGridView loadManagerLeftTreeJson(DeptVo deptVo) {
        // 查询出所有的部门，存放进list中
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        Integer belongId = activerUser.getUser().getId();

        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belong_user",belongId);
        List<Dept> list = deptService.list(queryWrapper);

        List<TreeNode> treeNodes = new ArrayList<>();
        // 将部门放入treeNodes中，组装成json

        Integer lang = activerUser.getUser().getLoginLang();
        for (Dept dept : list) {
            if(lang==2&&dept.getName().equals("其它")){
                dept.setName("ohters");
            }

            Boolean open = dept.getOpen() == 1 ? true : false;
            treeNodes.add(new TreeNode(dept.getId(), dept.getPid(), dept.getName(), open));
        }
        return new DataGridView(treeNodes);
    }


    @RequestMapping("loadGateUnscheduled")
    public DataGridView loadGateUnscheduled(Integer authId) {
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        Integer belongId = activerUser.getUser().getId();
        List<Integer> deptarr = new ArrayList<Integer>();
        List<Staff> staffs = new ArrayList<>();

        QueryWrapper<Staff> staffQueryWrapper =new QueryWrapper<>();
        staffQueryWrapper.eq("belong_id",belongId);
        staffQueryWrapper.eq("available",1);
        staffs=staffService.list(staffQueryWrapper);

        List<String> idList = new ArrayList<>();
        if (authId != null) {
            GateAuthor gateAuthor = authorService.getGateAuthorById(authId);
            String staffList = gateAuthor.getStaffList();
            if (!StringUtil.isEmpty(staffList)) {
                idList = Arrays.asList(staffList.split(","));
            }
        }

//        List<Integer> removeList = new ArrayList<>();
//        QueryWrapper<GateAuthor> authorQueryWrapper=new QueryWrapper<>();
//        authorQueryWrapper.eq("belong_id",belongId);
//        List<GateAuthor> auth=authorService.list(authorQueryWrapper);
//        for (int i = 0; i <auth.size() ; i++) {
//            GateAuthor ga =auth.get(i);
//            if(ga.getId()!=authId){
//
//            }
//        }

        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belong_user",belongId);
        List<Dept> list = deptService.list(queryWrapper);


        List<DTree> treeNodes = new ArrayList<>();

        List<DTree> userNodes = new ArrayList<>();
        // 将部门放入treeNodes中，组装成json
        Integer otherCount=0;//记录没有部门的人数
        for (Staff staff : staffs) {
            if (staff.getDeptId() != null) {
                String Checked = "0";
                for (int i = 0; i < idList.size(); i++) {
                    if (staff.getId() == Integer.parseInt(idList.get(i))) {
                        Checked = "1";
                    }
                }

                Integer deptId = staff.getDeptId();
                while (deptId < 10000) {
                    deptId = deptId * 10;
                }

                DTree node = new DTree(deptId + "" + staff.getId(), staff.getDeptId() + "", staff.getStaffName(), false);
                node.setCheckArr(Checked);
                userNodes.add(node);
            }else {
                otherCount+=1;

            }

        }
        for (Dept dept : list) {
            Boolean open = dept.getOpen() == 1 ? true : false;
            String checked = "0";
            if (deptarr.contains(dept.getId())) {
                checked = "1";
            }
            DTree node = new DTree(dept.getId() + "", dept.getPid() + "", dept.getName(), open);
            node.setCheckArr(checked);
            treeNodes.add(node);
        }


        treeNodes.addAll(userNodes);

        return new DataGridView(treeNodes);
    }

    @RequestMapping("loadAttendUnscheduled")
    public DataGridView loadAttendUnscheduled(Integer asId) {
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        Integer belongId = activerUser.getUser().getId();
        List<Integer> deptarr = new ArrayList<Integer>();

        List<Staff> staffs = staffService.getAttendUnscheduled(belongId);


        List<String> idList = new ArrayList<>();

        if (asId != null) {
            AttendStaff attendStaff = attendStaffService.getById(asId);
            String staffList = attendStaff.getStaffList();
            if (!StringUtil.isEmpty(staffList)) {
                idList = Arrays.asList(staffList.split(","));
            }
        }


        System.out.println("staffs(uns) " + staffs.size());


        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belong_user",belongId);
        List<Dept> list = deptService.list(queryWrapper);

        List<DTree> treeNodes = new ArrayList<>();

        List<DTree> userNodes = new ArrayList<>();
        // 将部门放入treeNodes中，组装成json

        for (Staff staff : staffs) {
            if (staff.getDeptId() != null) {
                Integer deptId = staff.getDeptId();
                while (deptId < 10000) {
                    deptId = deptId * 10;
                }
                userNodes.add(new DTree(deptId + "" + staff.getId(), staff.getDeptId() + "", staff.getStaffName(), false));

            }
        }

        for (int i = 0; i < idList.size(); i++) {
            System.out.println("extraStaff " + idList.get(i));

            if (!StringUtil.isEmpty(idList.get(i)) && Integer.valueOf(idList.get(i)) != null) {
                Staff newStaff = staffService.getStaffById(Integer.parseInt(idList.get(i)));
                if(newStaff!=null){
                    Integer deptId = newStaff.getDeptId();
                    if (newStaff != null) {
                        System.out.println("add " + newStaff.getStaffName());
                        while (deptId < 10000) {
                            deptId = deptId * 10;
                        }
                        DTree node = new DTree(deptId + "" + newStaff.getId(), newStaff.getDeptId() + "", newStaff.getStaffName(), false);
                        node.setCheckArr("1");
                        userNodes.add(node);
                    }
                }

            }


        }

        for (Dept dept : list) {
            Boolean open = dept.getOpen() == 1 ? true : false;
            String checked = "0";
            if (deptarr.contains(dept.getId())) {
                checked = "1";
            }
            DTree node = new DTree(dept.getId() + "", dept.getPid() + "", dept.getName(), open);
            node.setCheckArr(checked);
            treeNodes.add(node);
        }

        treeNodes.addAll(userNodes);

        return new DataGridView(treeNodes);
    }


    @RequestMapping("loadStaffByCard")
    public DataGridView loadStaffByCard(Integer staffId) {
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        Integer belongId = activerUser.getUser().getId();
        List<Staff> staffs = staffService.getStaffsByBelongId(belongId);
        List<String> idList = new ArrayList<>();
        String ids = cardService.getBinded(belongId);
        if(!StringUtil.isEmpty(ids)){
            idList = Arrays.asList(ids.split(","));
        }

        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belong_user",belongId);
        List<Dept> list = deptService.list(queryWrapper);

        List<DTree> treeNodes = new ArrayList<>();

        List<DTree> userNodes = new ArrayList<>();
        // 将部门放入treeNodes中，组装成json

        for (Staff staff : staffs) {
            if(staffId!=null&&staff.getId()==staffId){
                if(idList.contains(staffId+"")){
                    Integer deptId = staff.getDeptId();

                    while (deptId < 10000) {
                        deptId = deptId * 10;
                    }


                    userNodes.add(new DTree(deptId + "" + staff.getId(), staff.getDeptId() + "", staff.getStaffName(), false));

                }
            }


            if (staff.getDeptId() != null&&!idList.contains(staff.getId()+"")) {
                Integer deptId = staff.getDeptId();

                while (deptId < 10000) {
                    deptId = deptId * 10;
                }


                userNodes.add(new DTree(deptId + "" + staff.getId(), staff.getDeptId() + "", staff.getStaffName(), false));

            }
        }


        for (Dept dept : list) {
            DTree node = new DTree(dept.getId() + "", dept.getPid() + "", dept.getName(), true);
            treeNodes.add(node);
        }

        treeNodes.addAll(userNodes);

        return new DataGridView(treeNodes);
    }


    /**
     * 查询所有部门数据
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("loadAllDept")
    public DataGridView loadAllDept(DeptVo deptVo) {
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        Integer belongId = activerUser.getUser().getId();
        IPage<Dept> page = new Page<>(deptVo.getPage(), deptVo.getLimit());
        // 进行模糊查询
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getName()), "name", deptVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getRemark()), "remark", deptVo.getRemark());
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getAddress()), "address", deptVo.getAddress());
        queryWrapper.eq(deptVo.getId() != null, "id", deptVo.getId()).or().eq(deptVo.getId() != null, "pid", deptVo.getId());
        queryWrapper.orderByAsc("ordernum");
        // 进行查询
        queryWrapper.eq("belong_user",belongId);

        deptService.page(page, queryWrapper);

        Integer lang = activerUser.getUser().getLoginLang();
        if(lang==2){
            List<Dept> list= page.getRecords();
            for (Dept dept : list) {
                if(lang==2&&dept.getName().equals("其它")){
                    dept.setName("ohters");
                }
            }
        }
        // 返回DataGridView
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加部门
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("addDept")
    public ResultObj addDept(@Valid DeptVo deptVo, BindingResult bResult) {

        try {
            if(bResult.hasErrors()) {
                String errrorMsg = bResult.getFieldError().getDefaultMessage();
                return new ResultObj(400,errrorMsg);
            }
            Subject subject = SecurityUtils.getSubject();
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            Integer belongId = activerUser.getUser().getId();

            QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("belong_user",belongId);
            queryWrapper.eq("name",deptVo.getName());
            queryWrapper.eq("available",1);
            if(deptService.list(queryWrapper).size()>0){
                return new ResultObj(500,"dept_exist");
            }


            if(deptVo.getPid()==null){
                deptVo.setPid(0);
            }
            deptVo.setCreatetime(new Date());
            deptVo.setBelongUser(belongId);
            deptService.save(deptVo);
            return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
        }
    }

    /**
     * 加载排序码
     *
     * @return
     */
    @RequestMapping("loadDeptMaxOrderNum")
    public Map<String, Object> loadDeptMaxOrderNum() {
        Map<String, Object> map = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		Integer belongId = activerUser.getUser().getId();
		Integer value = deptService.loadDeptOrderNum(belongId);
        map.put("value", value);
        return map;
    }

    /**
     * 更新部门
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("updateDept")
    public ResultObj updateDept(@Valid DeptVo deptVo, BindingResult bResult) {
        try {
            if(bResult.hasErrors()) {
                String errrorMsg = bResult.getFieldError().getDefaultMessage();
                return new ResultObj(400,errrorMsg);
            }
            deptService.updateById(deptVo);
            return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
        }
    }

    /**
     * 检查当前部门是否有子部门
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("checkDeptHasChildrenNode")
    public Map<String, Object> checkDeptHasChildrenNode(DeptVo deptVo) {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", deptVo.getId());
        List<Dept> list = deptService.list(queryWrapper);
        if (list.size() > 0) {
            map.put("value", true);
        } else {
            map.put("value", false);
        }
        return map;
    }

    /**
     * 删除部门
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("deleteDept")
    public ResultObj deleteDept(DeptVo deptVo) {
        try {
            Dept dept = deptService.getDeptById(deptVo.getId());
//            if (dept != null && dept.getPid() == 0) {
//                return new ResultObj(500, "不可删除最高级的部门");
//            }

            QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("dept_id", dept.getId());
            queryWrapper.eq("available",1);
            if (staffService.list(queryWrapper).size() > 0) {
                return new ResultObj(500, "dept.deleteStaff");
            }

            deptService.removeById(deptVo.getId());
            return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
        }
    }

}
