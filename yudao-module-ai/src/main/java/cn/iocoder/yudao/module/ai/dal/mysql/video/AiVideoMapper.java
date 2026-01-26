package cn.iocoder.yudao.module.ai.dal.mysql.video;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ai.controller.admin.video.vo.AiVideoPageReqVO;
import cn.iocoder.yudao.module.ai.dal.dataobject.video.AiVideoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AI 视频 Mapper
 *
 * @author ruoyi
 */
@Mapper
public interface AiVideoMapper extends BaseMapperX<AiVideoDO> {

    default AiVideoDO selectByTaskId(String taskId) {
        return selectOne(AiVideoDO::getTaskId, taskId);
    }

    default PageResult<AiVideoDO> selectPage(AiVideoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AiVideoDO>()
                .eqIfPresent(AiVideoDO::getUserId, reqVO.getUserId())
                .eqIfPresent(AiVideoDO::getPlatform, reqVO.getPlatform())
                .eqIfPresent(AiVideoDO::getStatus, reqVO.getStatus())
                .likeIfPresent(AiVideoDO::getPrompt, reqVO.getPrompt())
                .betweenIfPresent(AiVideoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AiVideoDO::getId));
    }

    default PageResult<AiVideoDO> selectPageMy(Long userId, AiVideoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AiVideoDO>()
                .eq(AiVideoDO::getUserId, userId)
                .likeIfPresent(AiVideoDO::getPrompt, reqVO.getPrompt())
                .eqIfPresent(AiVideoDO::getStatus, reqVO.getStatus())
                .orderByDesc(AiVideoDO::getId));
    }

    default List<AiVideoDO> selectListByStatus(Integer status) {
        return selectList(AiVideoDO::getStatus, status);
    }

    default List<AiVideoDO> selectListByStatusAndPlatform(Integer status, String platform) {
        return selectList(AiVideoDO::getStatus, status,
                AiVideoDO::getPlatform, platform);
    }

}
