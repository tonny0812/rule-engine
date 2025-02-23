package cn.ruleengine.web.vo.rule;

import cn.ruleengine.core.rule.Rule;
import cn.ruleengine.web.enums.RuleStatus;
import cn.ruleengine.web.vo.condition.ConditionGroupConfig;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dingqianwen
 * @date 2020/8/24
 * @since 1.0.0
 */
@Data
public class UpdateRuleRequest {

    @NotNull
    private Integer id;

    private Integer status = RuleStatus.EDIT.getStatus();

    private List<ConditionGroupConfig> conditionGroup;

    private Action action;

    private DefaultAction defaultAction;

    private Rule.AbnormalAlarm abnormalAlarm = new Rule.AbnormalAlarm();

}
