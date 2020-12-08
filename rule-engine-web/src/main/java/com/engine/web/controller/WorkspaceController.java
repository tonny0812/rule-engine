/**
 * Copyright (c) 2020 dingqianwen (761945125@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.engine.web.controller;

import com.engine.web.service.WorkspaceService;
import com.engine.web.vo.base.request.IdRequest;
import com.engine.web.vo.base.response.BaseResult;
import com.engine.web.vo.base.response.PlainResult;
import com.engine.web.vo.workspace.Workspace;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 丁乾文
 * @create 2020/11/21
 * @since 1.0.0
 */
@Api(tags = "工作空间控制器")
@RestController
@RequestMapping("workspace")
public class WorkspaceController {

    @Resource
    private WorkspaceService workspaceService;

    /**
     * 用户有权限的工作空间列表
     *
     * @return list
     */
    @PostMapping("list")
    @ApiOperation("用户有权限的工作空间列表")
    public BaseResult list() {
        PlainResult<List<Workspace>> plainResult = new PlainResult<>();
        plainResult.setData(workspaceService.list());
        return plainResult;
    }

    /**
     * 切换工作空间
     *
     * @param idRequest 工作空间id
     * @return true
     */
    @PostMapping("change")
    @ApiOperation("切换工作空间")
    public BaseResult change(@RequestBody @Valid IdRequest idRequest) {
        PlainResult<Boolean> plainResult = new PlainResult<>();
        plainResult.setData(workspaceService.change(idRequest.getId()));
        return plainResult;
    }

    /**
     * 获取当前工作空间
     *
     * @return Workspace
     */
    @PostMapping("currentWorkspace")
    @ApiOperation("当前工作空间")
    public BaseResult currentWorkspace() {
        PlainResult<Workspace> plainResult = new PlainResult<>();
        plainResult.setData(workspaceService.currentWorkspace());
        return plainResult;
    }
}
