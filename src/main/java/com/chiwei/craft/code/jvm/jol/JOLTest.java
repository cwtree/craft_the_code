package com.chiwei.craft.code.jvm.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class JOLTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user1 = User.builder().age(30).username("chiwei").build();
		System.out.println("====对象内部信息====");
		System.out.println(ClassLayout.parseInstance(user1).toPrintable());
		System.out.println("====对象外部信息====");
		System.out.println(GraphLayout.parseInstance(user1).toPrintable());
		System.out.println("====对象大小信息====");
		System.out.println(GraphLayout.parseInstance(user1).totalSize());
		
	}

}

@Getter
@Setter
@ToString
@Builder
class User {
	private int age;
	private String username;
}
