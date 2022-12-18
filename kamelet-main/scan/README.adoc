== Slack Source Example

In this sample you'll use the Slack Source Kamelet throught camel-kamelet-main

=== Setup the Slack Bot App

1. Go to https://api.slack.com/apps and create an app
2. Set the following permissions to this app, in particular in relation to Bot Token
- channels:history
- groups:history
- im:history
- mpim:history
- channels:read
- groups:read
- im:read
- mpim:read
3. Enable incoming webhook for this Bot App
4. Copy the User OAuth Token 
5. Open the slack-integration.properties file and paste the token as token property
6. Set the correct channel name

=== Run

Run the following command:

```
> mvn clean install
> mvn camel:dev
```

You should see

```
[INFO] Starting Camel ...
18:07:31.131 [org.apache.camel.main.KameletMain.main()] INFO  o.a.c.i.engine.AbstractCamelContext - Detected: camel-debug JAR (enabling Camel Debugging)
18:07:32.711 [org.apache.camel.main.KameletMain.main()] INFO  o.apache.camel.main.DownloaderHelper - Downloaded dependency: org.apache.camel:camel-kamelet:3.15.0 took: 1s492ms
18:07:32.787 [org.apache.camel.main.KameletMain.main()] INFO  o.apache.camel.main.BaseMainSupport - Auto-configuration summary
18:07:32.788 [org.apache.camel.main.KameletMain.main()] INFO  o.apache.camel.main.BaseMainSupport -     camel.main.name=Slack-source
18:07:32.788 [org.apache.camel.main.KameletMain.main()] INFO  o.apache.camel.main.BaseMainSupport -     camel.main.routesReloadDirectoryRecursive=true
18:07:32.788 [org.apache.camel.main.KameletMain.main()] INFO  o.apache.camel.main.BaseMainSupport -     camel.main.routesReloadDirectory=/home/oscerd/workspace/miscellanea/kamelet-samples/camel-kamelet-main/slack-source/src/main/resources
18:07:32.788 [org.apache.camel.main.KameletMain.main()] INFO  o.apache.camel.main.BaseMainSupport -     camel.main.durationMaxAction=stop
18:07:32.788 [org.apache.camel.main.KameletMain.main()] INFO  o.apache.camel.main.BaseMainSupport -     camel.main.routesReloadEnabled=true
18:07:32.788 [org.apache.camel.main.KameletMain.main()] INFO  o.apache.camel.main.BaseMainSupport -     camel.main.sourceLocationEnabled=true
18:07:32.788 [org.apache.camel.main.KameletMain.main()] INFO  o.apache.camel.main.BaseMainSupport -     camel.main.routesReloadPattern=*.xml,*.yaml,*.java
18:07:32.788 [org.apache.camel.main.KameletMain.main()] INFO  o.apache.camel.main.BaseMainSupport -     camel.component.kamelet.location=classpath:/kamelets,github:apache:camel-kamelets/kamelets
18:07:32.966 [org.apache.camel.main.KameletMain.main()] INFO  o.apache.camel.main.DownloaderHelper - Downloaded dependency: org.apache.camel:camel-core-languages:3.15.0 took: 79ms
18:07:32.969 [org.apache.camel.main.KameletMain.main()] INFO  o.a.c.m.JmxManagementStrategy - JMX is enabled
18:07:33.826 [org.apache.camel.main.KameletMain.main()] INFO  o.apache.camel.main.DownloaderHelper - Downloaded dependency: org.apache.camel:camel-gson:3.15.0 took: 112ms
18:07:33.949 [org.apache.camel.main.KameletMain.main()] INFO  o.apache.camel.main.DownloaderHelper - Downloaded dependency: org.apache.camel:camel-slack:3.15.0 took: 122ms
18:07:35.088 [org.apache.camel.main.KameletMain.main()] INFO  o.apache.camel.main.DownloaderHelper - Downloaded dependency: org.apache.camel:camel-log:3.15.0 took: 768ms
18:07:35.273 [org.apache.camel.main.KameletMain.main()] INFO  o.a.c.s.FileWatcherResourceReloadStrategy - Starting ReloadStrategy to watch directory: /home/oscerd/workspace/miscellanea/kamelet-samples/camel-kamelet-main/slack-source/src/main/resources
18:07:36.242 [org.apache.camel.main.KameletMain.main()] INFO  o.a.c.i.engine.AbstractCamelContext - Routes startup (total:3 started:3)
18:07:36.242 [org.apache.camel.main.KameletMain.main()] INFO  o.a.c.i.engine.AbstractCamelContext -     Started route1 (kamelet://slack-source)
18:07:36.242 [org.apache.camel.main.KameletMain.main()] INFO  o.a.c.i.engine.AbstractCamelContext -     Started slack-source-1 (slack://general)
18:07:36.242 [org.apache.camel.main.KameletMain.main()] INFO  o.a.c.i.engine.AbstractCamelContext -     Started log-sink-2 (kamelet://source)
18:07:36.242 [org.apache.camel.main.KameletMain.main()] INFO  o.a.c.i.engine.AbstractCamelContext - Apache Camel 3.15.0 (Slack-source) started in 3s502ms (build:135ms init:2s222ms start:1s145ms)
18:07:36.243 [org.apache.camel.main.KameletMain.main()] INFO  o.a.c.impl.debugger.BacklogDebugger - Enabling debugger
18:07:37.546 [Camel (Slack-source) thread #2 - slack://general] INFO  info - Exchange[ExchangePattern: InOnly, Headers: {Content-Type=application/json}, BodyType: byte[], Body: {"type":"message","team":"xxxx","user":"yyyy","text":"hello","blocks":[{"type":"rich_text","elements":[{"type":"rich_text_section","elements":[{"type":"text","text":"hello"}]}],"blockId":"XAE5"}],"ts":"1646919166.690039","is_intro":false,"is_starred":false,"wibblr":false,"displayAsBot":false,"upload":false,"clientMsgId":"4da336d2-fafe-4442-b307-d3ae2961ba3a","unfurlLinks":false,"unfurlMedia":false,"is_thread_broadcast":false,"is_locked":false,"subscribed":false,"hidden":false}]
^C18:07:41.739 [org.apache.camel.main.KameletMain.main()] INFO  o.a.c.i.engine.AbstractCamelContext - Apache Camel 3.15.0 (Slack-source) shutting down (timeout:45s)
18:07:41.773 [org.apache.camel.main.KameletMain.main()] INFO  o.a.c.i.engine.AbstractCamelContext - Routes stopped (total:3 stopped:3)
18:07:41.773 [org.apache.camel.main.KameletMain.main()] INFO  o.a.c.i.engine.AbstractCamelContext -     Stopped log-sink-2 (kamelet://source)
18:07:41.773 [org.apache.camel.main.KameletMain.main()] INFO  o.a.c.i.engine.AbstractCamelContext -     Stopped slack-source-1 (slack://general)
18:07:41.773 [org.apache.camel.main.KameletMain.main()] INFO  o.a.c.i.engine.AbstractCamelContext -     Stopped route1 (kamelet://slack-source)
18:07:43.286 [org.apache.camel.main.KameletMain.main()] INFO  o.a.c.impl.debugger.BacklogDebugger - Disabling debugger
18:07:43.292 [org.apache.camel.main.KameletMain.main()] INFO  o.a.c.i.engine.AbstractCamelContext - Apache Camel 3.15.0 (Slack-source) shutdown in 1s552ms (uptime:8s194ms)

```
