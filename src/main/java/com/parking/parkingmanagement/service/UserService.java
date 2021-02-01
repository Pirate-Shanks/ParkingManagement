package com.parking.parkingmanagement.service;

import com.parking.parkingmanagement.entity.Member;
import com.parking.parkingmanagement.entity.MemberExample;
import com.parking.parkingmanagement.exception.BusinessException;
import com.parking.parkingmanagement.exception.BusinessExceptionCode;
import com.parking.parkingmanagement.mapper.MemberMapper;
import com.parking.parkingmanagement.utils.CopyUtil;
import com.parking.parkingmanagement.vo.LoginUserVO;
import com.parking.parkingmanagement.vo.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Autowired
    MemberMapper memberMapper;

    public LoginUserVO login(MemberVO memberVO) {
        Member member = selectByLoginName(memberVO.getUserName());
        if (member == null) {
            LOG.info("用户名不存在, {}", memberVO.getUserName());
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        } else {
            if (member.getPassword().equals(memberVO.getPassword())) {
                // 登录成功
                LoginUserVO loginUserDto = CopyUtil.copy(member, LoginUserVO.class);
                // 为登录用户读取权限,暂时不做
                return loginUserDto;
            } else {
                LOG.info("密码不对, 输入密码：{}, 数据库密码：{}", memberVO.getPassword(), memberVO.getPassword());
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            }
        }
    }
    /**
     * 根据登录名查询用户信息
     * @param loginName
     * @return
     */
    public Member selectByLoginName(String loginName) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andUserNameEqualTo(loginName);
        List<Member> memberList = memberMapper.selectByExample(memberExample);
        if (CollectionUtils.isEmpty(memberList)) {
            return null;
        } else {
            return memberList.get(0);
        }
    }
}
