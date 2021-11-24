# McSR
Minecraft Server Runner Program. Written in Kotlin (GUI + CLI)

## 공지
이 프로젝트는 [qogusdn1017](https://github.com/qogusdn1017) 님의 [McSR](https://github.com/qogusdn1017/McSR) 프로젝트의 포크 버전입니다.
## 의존성
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization)
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines)

## 라이선스
MIT License

## 참고한 것들
- Paper의 터미널 시스템
- Kommand에서 영감을 얻은 DSL 구조

## 빌드하기
이 프로젝트는 GUI 시스템이 JavaFX를 이용하므로, JavaFX 런타임이 따로 필요합니다.
매번 설치하는 번거로움을 피하기 위해, JLink를 이용해 플랫폼별 네이티브 앱 형태로 배포합니다. 

```
> ./gradlew binary
```
를 실행하고, `builds/McSR-bin.zip`을 압축해제합니다. 그후, 압축 해제된 폴더의 `bin/McSR`(윈도우의 경우 `bin/McSR.bat`)을 실행하여 앱을 실행할 수 있습니다. 또는, 이를 바로가기를 이용하거나, 시스템 환경변수에 추가해 쉽게 접근할 수 있습니다.

## CLI 개발하기
개발하는 방법은 [CLI 개발하기](docs/cli.md) 를 참고하세요.