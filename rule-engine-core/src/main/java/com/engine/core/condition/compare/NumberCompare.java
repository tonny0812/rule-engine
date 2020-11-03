/**
 * Copyright @2020 dingqianwen
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
package com.engine.core.condition.compare;


import com.engine.core.condition.Operator;
import com.engine.core.condition.Compare;
import com.engine.core.exception.ConditionException;

import java.math.BigDecimal;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dingqianwen
 * @date 2020/4/6
 * @since 1.0.0
 */
public class NumberCompare implements Compare {


    private NumberCompare() {

    }

    private static NumberCompare numberCompare = new NumberCompare();

    public static NumberCompare getInstance() {
        return numberCompare;
    }


    @Override
    public boolean compare(Object leftValue, Operator operator, Object rightValue) {
        if (leftValue == null || rightValue == null) {
            return false;
        }
        if (!(leftValue instanceof Number) || !(rightValue instanceof Number)) {
            throw new ConditionException("左值/右值必须是NUMBER");
        }
        //处理数据类型转换为BigDecimal后运算
        BigDecimal lValue;
        BigDecimal rValue;
        if (leftValue instanceof BigDecimal) {
            lValue = (BigDecimal) leftValue;
        } else {
            lValue = new BigDecimal(String.valueOf(leftValue));
        }
        if (rightValue instanceof BigDecimal) {
            rValue = (BigDecimal) rightValue;
        } else {
            rValue = new BigDecimal(String.valueOf(rightValue));
        }
        int compare = lValue.compareTo(rValue);
        switch (operator) {
            case EQ:
                return compare == 0;
            case NE:
                return compare != 0;
            case GT:
                return compare > 0;
            case LT:
                return compare < 0;
            case GE:
                return compare == 0 || compare > 0;
            case LE:
                return compare == 0 || compare < 0;
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
    }
}
