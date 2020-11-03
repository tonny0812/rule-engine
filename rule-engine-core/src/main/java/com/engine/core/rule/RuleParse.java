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
package com.engine.core.rule;

import com.alibaba.fastjson.parser.ParserConfig;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dingqianwen
 * @date 2020/8/4
 * @since 1.0.0
 */
public interface RuleParse {

    ParserConfig PARSER_CONFIG = new ParserConfig() {
        {
            this.setAutoTypeSupport(true);
        }
    };

    /**
     * 规则json字符串转为rule对象
     *
     * @param jsonString 规则json字符串
     */
    void fromJson(String jsonString);

    /**
     * 规则信息转为json
     *
     * @return 规则json字符串
     */
    String toJson();

}