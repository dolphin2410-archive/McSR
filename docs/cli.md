# CLI 개발하기
McSR 프로젝트의 CLI 시스템은 개발자들이 개발하기 편하게 설계되었습니다. 
버킷의 커맨드 DSL인 [Kommand](https://github.com/monun/kommand) 에서 영감을 받아,
순수한 Java 터미널을 이용해 커맨드 시스템을 적용하였습니다.

## 공지
지금은 코드에 집중하고 나중에 추가하도록 합시다.

## 시작하기
일단 `CommandParser`을 상속하는 클래스를 만들어야합니다.
```kotlin
class MyCommandParser: CommandParser {
    override fun parse(commands: CommandArguments): ParseResult {
        
    }
}
```

### CommandArguments
CommandArguments는 사용자가 입력한 명령어를 String 배열의 형태로 묶은 것입니다. CommandArguments를 만드는 방법은 다음과 같습니다.
예를 들어, 다음과 같은 코드를 실행한다고 합시다.
```
> command option --flag
```



```kotlin
val arguments = CommandArguments(listOf("command", "option", "--flag"))
```
> 위 예제로 아래 코드를 설명하겠습니다.

#### parse
CommandArguments를 개발자들이 쉽게 파싱할 수 있도록 `parse` 메소드가 존재합니다. 다음과 같이 설정할 수 있습니다.
```kotin
arguments.parse {
    // Actions
}
```

`parse` 메소드는 아래에서 설명할 ParseResult를 반환합니다.

#### ParseResult
ParseResult는 사용자가 명령어를 입력해서 제대로 파싱이 됨 여부를 판단하기 위해 반환됩니다. `parse` 메소드에서는 `command` 메소드의 실행 여부에 따라 자동으로 반환합니다. 다음과 같이 설정합니다.


#### command
CommandArguments의 가장 첫번째 String 값 입니다. 기능은 아래 `option`과 같지만, 특수한 기능이 있습니다. 만약 실행되었을 시, `parse` 메소드는 `ParseResult.SUCCESS`를 반환합니다.

#### option
CommandArguments의 인수를 불러옵니다

### 플래그
CLI에서 `--<flag>` 형태로 존재하는 명령을 플래그라고 합니다. 일반 명령어와 달리, 플래그는 어느 위치에 놓아도 됩니다.
```kotlin

```

