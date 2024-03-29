/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.test.context.transaction;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * TransactionConfiguration defines class-level metadata for configuring
 * transactional tests.
 *
 * @author Sam Brannen
 * @since 2.5
 * @see ContextConfiguration
 * @see TransactionalTestExecutionListener
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface TransactionConfiguration {

	/**
	 * The bean name of the {@link PlatformTransactionManager} that is to be
	 * used to drive transactions. This attribute is not required and only needs
	 * to be specified explicitly if the bean name of the desired
	 * PlatformTransactionManager is not &quot;transactionManager&quot;.
	 */
	String transactionManager() default "transactionManager";

	/**
	 * Should transactions be rolled back by default?
	 */
	boolean defaultRollback() default true;

}
