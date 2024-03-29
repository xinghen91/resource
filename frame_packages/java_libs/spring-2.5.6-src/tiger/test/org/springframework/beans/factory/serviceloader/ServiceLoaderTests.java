/*
 * Copyright 2002-2007 the original author or authors.
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

package org.springframework.beans.factory.serviceloader;

import java.util.List;
import java.util.ServiceLoader;

import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.TestCase;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.JdkVersion;

/**
 * @author Juergen Hoeller
 */
public class ServiceLoaderTests extends TestCase {

	public void testServiceLoaderFactoryBean() {
		if (JdkVersion.getMajorJavaVersion() < JdkVersion.JAVA_16 ||
				!ServiceLoader.load(DocumentBuilderFactory.class).iterator().hasNext()){
			return;
		}

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		RootBeanDefinition bd = new RootBeanDefinition(ServiceLoaderFactoryBean.class);
		bd.getPropertyValues().addPropertyValue("serviceType", DocumentBuilderFactory.class.getName());
		bf.registerBeanDefinition("service", bd);
		ServiceLoader serviceLoader = (ServiceLoader) bf.getBean("service");
		assertTrue(serviceLoader.iterator().next() instanceof DocumentBuilderFactory);
	}

	public void testServiceFactoryBean() {
		if (JdkVersion.getMajorJavaVersion() < JdkVersion.JAVA_16 ||
				!ServiceLoader.load(DocumentBuilderFactory.class).iterator().hasNext()){
			return;
		}

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		RootBeanDefinition bd = new RootBeanDefinition(ServiceFactoryBean.class);
		bd.getPropertyValues().addPropertyValue("serviceType", DocumentBuilderFactory.class.getName());
		bf.registerBeanDefinition("service", bd);
		assertTrue(bf.getBean("service") instanceof DocumentBuilderFactory);
	}

	public void testServiceListFactoryBean() {
		if (JdkVersion.getMajorJavaVersion() < JdkVersion.JAVA_16 ||
				!ServiceLoader.load(DocumentBuilderFactory.class).iterator().hasNext()){
			return;
		}

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		RootBeanDefinition bd = new RootBeanDefinition(ServiceListFactoryBean.class);
		bd.getPropertyValues().addPropertyValue("serviceType", DocumentBuilderFactory.class.getName());
		bf.registerBeanDefinition("service", bd);
		List serviceList = (List) bf.getBean("service");
		assertTrue(serviceList.get(0) instanceof DocumentBuilderFactory);
	}

}
