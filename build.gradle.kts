plugins {
	java
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.szs"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

// compileClasspath : 소스 코드 컴파일 시 모든 클래스 파일과 라이브러리 포함, 필요한 의존성만 포함되며 결과물에는 반영안됨
// runtimeClasspath : 실행 시 필요한 모든 클래스 파일과 라이브러리 포함, 필요한 의존성만 포함하여 결과물에 포함
// runtimeOnly : 라이브러리가 런타임 시에만 필요함을 명시
// compileOnly : 컴파일 시점에만 필요함을 명시
// implementation : 컴파일 + 런타임이며, 빌드 결과물에도 포함된다. 코드 레벨에서 참조 가능
// annotationProcessor : 컴파일 단계에서 Annotation에 정의된 일렬의 프로세스를 동작하게 하는 것
// testImplementation : 테스트 단계에서 컴파일 + 런타임이며, 빌드 결과물에도 포함된다. 테스트 코드 레벨에서 참조 가능
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("io.jsonwebtoken:jjwt-api:0.12.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.h2database:h2")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
