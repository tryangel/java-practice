### 将本地主机的7001端口的请求转发到47.103.54.109的30095端口
```bash
# -f 后台执行
# -N 不执行远端命令，　也就是不登录进去
# -g 双向隧道
ssh -g -f -N -L 7001:47.103.54.109:30095 yituadmin@47.103.54.109
```

### 将远端主机的7002端口转发到本地的8095端口
```bash
ssh -g -f -N -R 7002:localhost:8095 yituadmin@47.103.54.109
```