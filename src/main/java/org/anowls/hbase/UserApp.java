/*
 * Copyright 2011-2012 the original author or authors.
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
package org.anowls.hbase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserApp {

	private static final Log log = LogFactory.getLog(UserApp.class);

	public static void main(String[] args) throws Exception {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"application-context.xml", UserApp.class);
		log.info("HBase Application Running");
		context.registerShutdownHook();
		
		UserUtils userUtils = context.getBean(UserUtils.class);		
		userUtils.initialize();
		userUtils.addUsers();
		
		UserRepository userRepository = context.getBean(UserRepository.class);
		List<User> users = userRepository.findAll();
		System.out.println("Number of users = " + users.size());
		System.out.println(users);
	
	}
}
