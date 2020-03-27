<template>
  <div>
    <div>
      <mybar></mybar>
    </div>
    <el-container style="height: 600px; border: 1px solid #eee">
      <el-aside width="18%" style="background-color: rgb(238, 241, 246)">
        <div class="out-container">
          <div class="user-detail" v-if="article.user">
          <div>
            <el-avatar :size="40">
              <img :src="baseUrl + article.user.picture"/>
            </el-avatar>
          </div>
          <div>
            <a href="#"><span style="color: black">&nbsp;&nbsp;{{article.user.userName}}&nbsp;&nbsp;</span></a>
          </div>
          <div>
            <el-button type="danger">私聊</el-button>
          </div>
        </div>
        <div>
          <div class="user-detail">
            <div @click="modifyRelation(1)">
              <svg v-if="praise" style="width: 50%;height:50%" t="1585143730331" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5311" width="200" height="200"><path d="M621.674667 408.021333c16.618667-74.24 28.224-127.936 34.837333-161.194666C673.152 163.093333 629.941333 85.333333 544.298667 85.333333c-77.226667 0-116.010667 38.378667-138.88 115.093334l-0.586667 2.24c-13.728 62.058667-34.72 110.165333-62.506667 144.586666a158.261333 158.261333 0 0 1-119.733333 58.965334l-21.909333 0.469333C148.437333 407.808 106.666667 450.816 106.666667 503.498667V821.333333c0 64.8 52.106667 117.333333 116.394666 117.333334h412.522667c84.736 0 160.373333-53.568 189.12-133.92l85.696-239.584c21.802667-60.96-9.536-128.202667-70.005333-150.186667a115.552 115.552 0 0 0-39.488-6.954667H621.674667z" p-id="5312" fill="#d81e06"></path></svg>
              <svg v-else style="width: 50%;height:50%"  t="1585130607188" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2623" width="200" height="200"><path d="M621.674667 408.021333c16.618667-74.24 28.224-127.936 34.837333-161.194666C673.152 163.093333 629.941333 85.333333 544.298667 85.333333c-77.226667 0-116.010667 38.378667-138.88 115.093334l-0.586667 2.24c-13.728 62.058667-34.72 110.165333-62.506667 144.586666a158.261333 158.261333 0 0 1-119.733333 58.965334l-21.909333 0.469333C148.437333 407.808 106.666667 450.816 106.666667 503.498667V821.333333c0 64.8 52.106667 117.333333 116.394666 117.333334h412.522667c84.736 0 160.373333-53.568 189.12-133.92l85.696-239.584c21.802667-60.96-9.536-128.202667-70.005333-150.186667a115.552 115.552 0 0 0-39.488-6.954667H621.674667zM544.256 149.333333c39.253333 0 59.498667 36.48 49.888 84.928-7.573333 38.144-21.984 104.426667-43.221333 198.666667-4.512 20.021333 10.56 39.093333 30.912 39.093333h218.666666c6.101333 0 12.16 1.066667 17.909334 3.168 27.445333 9.984 41.674667 40.554667 31.776 68.266667l-85.568 239.573333C744.981333 838.026667 693.301333 874.666667 635.402667 874.666667H223.498667C194.314667 874.666667 170.666667 850.784 170.666667 821.333333V503.498667c0-17.866667 14.144-32.448 31.829333-32.821334l21.866667-0.469333a221.12 221.12 0 0 0 167.381333-82.56c34.346667-42.602667 59.146667-99.306667 74.869333-169.877333C482.101333 166.336 499.552 149.333333 544.266667 149.333333z" p-id="2624" fill="#707070"></path></svg>
              <div class="word">
                {{praiseCount}}赞
              </div>
            </div>
            <div @click="modifyRelation(3)">
              <svg v-if="follow" style="width: 50%;height:50%" t="1585198347924" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2623" width="200" height="200"><path d="M667.786667 117.333333C832.864 117.333333 938.666667 249.706667 938.666667 427.861333c0 138.250667-125.098667 290.506667-371.573334 461.589334a96.768 96.768 0 0 1-110.186666 0C210.432 718.368 85.333333 566.112 85.333333 427.861333 85.333333 249.706667 191.136 117.333333 356.213333 117.333333c59.616 0 100.053333 20.832 155.786667 68.096C567.744 138.176 608.170667 117.333333 667.786667 117.333333z" p-id="2624" fill="#d81e06"></path></svg>
              <svg v-else style="width: 50%;height:50%" t="1585198372253" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2857" width="200" height="200"><path d="M667.786667 117.333333C832.864 117.333333 938.666667 249.706667 938.666667 427.861333c0 138.250667-125.098667 290.506667-371.573334 461.589334a96.768 96.768 0 0 1-110.186666 0C210.432 718.368 85.333333 566.112 85.333333 427.861333 85.333333 249.706667 191.136 117.333333 356.213333 117.333333c59.616 0 100.053333 20.832 155.786667 68.096C567.744 138.176 608.170667 117.333333 667.786667 117.333333z m0 63.146667c-41.44 0-70.261333 15.189333-116.96 55.04-2.165333 1.845333-14.4 12.373333-17.941334 15.381333a32.32 32.32 0 0 1-41.770666 0c-3.541333-3.018667-15.776-13.536-17.941334-15.381333-46.698667-39.850667-75.52-55.04-116.96-55.04C230.186667 180.48 149.333333 281.258667 149.333333 426.698667 149.333333 537.6 262.858667 675.242667 493.632 834.826667a32.352 32.352 0 0 0 36.736 0C761.141333 675.253333 874.666667 537.6 874.666667 426.698667c0-145.44-80.853333-246.218667-206.88-246.218667z" p-id="2858" fill="#707070"></path></svg>
              <div class="word">
                {{fansCount}}粉丝
              </div>
            </div>
           </div>
          </div>
        </div>
      </el-aside>
      <el-container>
        <el-header style="text-align: left;">
          <h1 style="font-size: 20px">{{article.title}}</h1>
          <div class="title-detail">
            <span>发布时间：{{article.updateTime}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <span>分类：<el-tag v-for="kind in article.kinds" :key="kind.id" type="success" style="margin-right: 10px;">{{kind.kindName}}</el-tag></span>
          </div>
        </el-header>
        <el-main class="content">
          <div v-html="article.content" class="my-border">
          </div>
          <div class="common">
            <div>
              <h4>全部评论&nbsp;({{commons.length}})</h4>
            </div>
            <div class="infinite-list-wrapper" style="overflow:auto" v-if="article.id">
              <div  class="list" v-infinite-scroll="load">
                <div v-for="item in commons" :key="item.floor" class="common-border">
                  <div class="common-out">
                    <div class="common-bar">
                      <el-avatar :size="30">
                        <img :src="baseUrl + item.user.picture"/>
                      </el-avatar>
                    </div>
                    <div class="common-content">
                      <div class="common-content-detail">
                        <div style="color: ##7e7e7e">
                          <b>{{item.user.userName}}</b>
                        </div>
                        <div style="color: #969696;fontSize:50%">
                          <span>{{item.floor}}楼&nbsp;</span>
                          <span>{{item.updateTime}}</span>
                        </div>
                      </div>
                      <div>
                        <span style="color: #404040">{{item.content}}</span>
                      </div>
                      <div>
                        <span v-if="item.praised" @click="praiseCommon(item)">
                          <svg style="width: 20px;height:20px" t="1585314741281" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2623" width="200" height="200"><path d="M621.674667 408.021333c16.618667-74.24 28.224-127.936 34.837333-161.194666C673.152 163.093333 629.941333 85.333333 544.298667 85.333333c-77.226667 0-116.010667 38.378667-138.88 115.093334l-0.586667 2.24c-13.728 62.058667-34.72 110.165333-62.506667 144.586666a158.261333 158.261333 0 0 1-119.733333 58.965334l-21.909333 0.469333C148.437333 407.808 106.666667 450.816 106.666667 503.498667V821.333333c0 64.8 52.106667 117.333333 116.394666 117.333334h412.522667c84.736 0 160.373333-53.568 189.12-133.92l85.696-239.584c21.802667-60.96-9.536-128.202667-70.005333-150.186667a115.552 115.552 0 0 0-39.488-6.954667H621.674667z" p-id="2624" fill="#d81e06"></path></svg>
                          <span style="color: red;fontSize:4px">{{item.praiseCount}}</span>
                        </span>
                        <span v-else @click="praiseCommon(item)">
                          <svg style="width: 20px;height:20px" t="1585199681019" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3235" width="200" height="200"><path d="M621.674667 408.021333c16.618667-74.24 28.224-127.936 34.837333-161.194666C673.152 163.093333 629.941333 85.333333 544.298667 85.333333c-77.226667 0-116.010667 38.378667-138.88 115.093334l-0.586667 2.24c-13.728 62.058667-34.72 110.165333-62.506667 144.586666a158.261333 158.261333 0 0 1-119.733333 58.965334l-21.909333 0.469333C148.437333 407.808 106.666667 450.816 106.666667 503.498667V821.333333c0 64.8 52.106667 117.333333 116.394666 117.333334h412.522667c84.736 0 160.373333-53.568 189.12-133.92l85.696-239.584c21.802667-60.96-9.536-128.202667-70.005333-150.186667a115.552 115.552 0 0 0-39.488-6.954667H621.674667zM544.256 149.333333c39.253333 0 59.498667 36.48 49.888 84.928-7.573333 38.144-21.984 104.426667-43.221333 198.666667-4.512 20.021333 10.56 39.093333 30.912 39.093333h218.666666c6.101333 0 12.16 1.066667 17.909334 3.168 27.445333 9.984 41.674667 40.554667 31.776 68.266667l-85.568 239.573333C744.981333 838.026667 693.301333 874.666667 635.402667 874.666667H223.498667C194.314667 874.666667 170.666667 850.784 170.666667 821.333333V503.498667c0-17.866667 14.144-32.448 31.829333-32.821334l21.866667-0.469333a221.12 221.12 0 0 0 167.381333-82.56c34.346667-42.602667 59.146667-99.306667 74.869333-169.877333C482.101333 166.336 499.552 149.333333 544.266667 149.333333z" p-id="3236" fill="#8a8a8a"></path></svg>
                          <span style="color: #969696;fontSize:4px">赞</span>
                        </span>
                        <span style="margin-left: 30px" @click="item.status = 1">
                          <svg style="width: 20px;height:20px" t="1585199866320" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3565" width="200" height="200"><path d="M157.568 751.296c-11.008-18.688-18.218667-31.221333-21.802667-37.909333A424.885333 424.885333 0 0 1 85.333333 512C85.333333 276.362667 276.362667 85.333333 512 85.333333s426.666667 191.029333 426.666667 426.666667-191.029333 426.666667-426.666667 426.666667a424.778667 424.778667 0 0 1-219.125333-60.501334 2786.56 2786.56 0 0 0-20.053334-11.765333l-104.405333 28.48c-23.893333 6.506667-45.802667-15.413333-39.285333-39.296l28.437333-104.288z m65.301333 3.786667l-17.258666 63.306666 63.306666-17.258666a32 32 0 0 1 24.522667 3.210666 4515.84 4515.84 0 0 1 32.352 18.944A360.789333 360.789333 0 0 0 512 874.666667c200.298667 0 362.666667-162.368 362.666667-362.666667S712.298667 149.333333 512 149.333333 149.333333 311.701333 149.333333 512c0 60.586667 14.848 118.954667 42.826667 171.136 3.712 6.912 12.928 22.826667 27.370667 47.232a32 32 0 0 1 3.338666 24.714667z m145.994667-70.773334a32 32 0 1 1 40.917333-49.205333A159.189333 159.189333 0 0 0 512 672c37.888 0 73.674667-13.173333 102.186667-36.885333a32 32 0 0 1 40.917333 49.216A223.178667 223.178667 0 0 1 512 736a223.178667 223.178667 0 0 1-143.136-51.690667z" p-id="3566" fill="#8a8a8a"></path></svg>
                          <span style="color: #969696;fontSize:50%">回复</span>
                        </span>
                      </div>
                      <div>
                        <div v-for="reply in item.replies" :key="reply.id">
                          <div class="common-out" >
                            <div class="common-reply">
                              <el-avatar :size="30">
                                <img :src="baseUrl + reply.user.picture"/>
                              </el-avatar>
                            </div>
                            <div class="common-content">
                              <div class="common-content-detail">
                                <div style="color: ##7e7e7e">
                                  <b>{{reply.user.userName}}</b>
                                </div>
                                <div style="color: #969696;fontSize:50%;width: 200%">
                                  <span>{{reply.updateTime}}</span>
                                </div>
                              </div>
                              <div>
                                <span style="color: #404040">{{reply.content}}</span>
                              </div>
                              <div>
                                <span @click="item.status = 1">
                                  <svg style="width: 20px;height:20px" t="1585199866320" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3565" width="200" height="200"><path d="M157.568 751.296c-11.008-18.688-18.218667-31.221333-21.802667-37.909333A424.885333 424.885333 0 0 1 85.333333 512C85.333333 276.362667 276.362667 85.333333 512 85.333333s426.666667 191.029333 426.666667 426.666667-191.029333 426.666667-426.666667 426.666667a424.778667 424.778667 0 0 1-219.125333-60.501334 2786.56 2786.56 0 0 0-20.053334-11.765333l-104.405333 28.48c-23.893333 6.506667-45.802667-15.413333-39.285333-39.296l28.437333-104.288z m65.301333 3.786667l-17.258666 63.306666 63.306666-17.258666a32 32 0 0 1 24.522667 3.210666 4515.84 4515.84 0 0 1 32.352 18.944A360.789333 360.789333 0 0 0 512 874.666667c200.298667 0 362.666667-162.368 362.666667-362.666667S712.298667 149.333333 512 149.333333 149.333333 311.701333 149.333333 512c0 60.586667 14.848 118.954667 42.826667 171.136 3.712 6.912 12.928 22.826667 27.370667 47.232a32 32 0 0 1 3.338666 24.714667z m145.994667-70.773334a32 32 0 1 1 40.917333-49.205333A159.189333 159.189333 0 0 0 512 672c37.888 0 73.674667-13.173333 102.186667-36.885333a32 32 0 0 1 40.917333 49.216A223.178667 223.178667 0 0 1 512 736a223.178667 223.178667 0 0 1-143.136-51.690667z" p-id="3566" fill="#8a8a8a"></path></svg>
                                  <span style="color: #969696;fontSize:50%">回复</span>
                                </span>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div v-if="item.status" class="reply-flex">
                        <div style="width: 200%">
                          <el-input
                          style="width: 600px"
                          type="text"
                          :rows="2"
                          placeholder="请输入内容"
                          v-model="item.reply">
                        </el-input>
                        </div>
                        <div>
                          <el-button @click="reply(item)" :disabled="!item.reply || item.reply.trim().length === 0" type="danger">发表</el-button>
                        </div>
                        <div>
                          <el-button @click="item.status = 0" type="info">取消</el-button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <p style="text-align:center;color: #969696;fontSize:50%" v-if="loadingCommons">加载中...</p>
                <p style="text-align:center;color: #969696;fontSize:50%" v-if="nomore">没有更多了……</p>
              </div>
            </div>
          </div>
        </el-main>
        <el-footer>
          <div class="user-common">
            <div>
              <el-avatar :size="40" class="user-img">
                <img :src="baseUrl + user.picture"/>
              </el-avatar>
            </div>
            <div>
              <el-input style="width:700px" placeholder="写下你的评论……" v-model="commonContent"></el-input>
            </div>
            <div>
              <el-button @click="publishTopic" type="warning" :disabled="commonContent.trim().length == 0">发表</el-button>
            </div>
          </div>
        </el-footer>
      </el-container>
      <el-aside width="8%" style="background-color: #f9f9f9">
          <div class="article-data">
            <div @click="modifyRelation(4)">
              <svg v-if="praiseArticle" style="width: 50%;height:50%" t="1585143730331" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5311" width="200" height="200"><path d="M621.674667 408.021333c16.618667-74.24 28.224-127.936 34.837333-161.194666C673.152 163.093333 629.941333 85.333333 544.298667 85.333333c-77.226667 0-116.010667 38.378667-138.88 115.093334l-0.586667 2.24c-13.728 62.058667-34.72 110.165333-62.506667 144.586666a158.261333 158.261333 0 0 1-119.733333 58.965334l-21.909333 0.469333C148.437333 407.808 106.666667 450.816 106.666667 503.498667V821.333333c0 64.8 52.106667 117.333333 116.394666 117.333334h412.522667c84.736 0 160.373333-53.568 189.12-133.92l85.696-239.584c21.802667-60.96-9.536-128.202667-70.005333-150.186667a115.552 115.552 0 0 0-39.488-6.954667H621.674667z" p-id="5312" fill="#d81e06"></path></svg>
              <svg v-else style="width: 50%;height:50%"  t="1585130607188" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2623" width="200" height="200"><path d="M621.674667 408.021333c16.618667-74.24 28.224-127.936 34.837333-161.194666C673.152 163.093333 629.941333 85.333333 544.298667 85.333333c-77.226667 0-116.010667 38.378667-138.88 115.093334l-0.586667 2.24c-13.728 62.058667-34.72 110.165333-62.506667 144.586666a158.261333 158.261333 0 0 1-119.733333 58.965334l-21.909333 0.469333C148.437333 407.808 106.666667 450.816 106.666667 503.498667V821.333333c0 64.8 52.106667 117.333333 116.394666 117.333334h412.522667c84.736 0 160.373333-53.568 189.12-133.92l85.696-239.584c21.802667-60.96-9.536-128.202667-70.005333-150.186667a115.552 115.552 0 0 0-39.488-6.954667H621.674667zM544.256 149.333333c39.253333 0 59.498667 36.48 49.888 84.928-7.573333 38.144-21.984 104.426667-43.221333 198.666667-4.512 20.021333 10.56 39.093333 30.912 39.093333h218.666666c6.101333 0 12.16 1.066667 17.909334 3.168 27.445333 9.984 41.674667 40.554667 31.776 68.266667l-85.568 239.573333C744.981333 838.026667 693.301333 874.666667 635.402667 874.666667H223.498667C194.314667 874.666667 170.666667 850.784 170.666667 821.333333V503.498667c0-17.866667 14.144-32.448 31.829333-32.821334l21.866667-0.469333a221.12 221.12 0 0 0 167.381333-82.56c34.346667-42.602667 59.146667-99.306667 74.869333-169.877333C482.101333 166.336 499.552 149.333333 544.266667 149.333333z" p-id="2624" fill="#707070"></path></svg>
              <div class="word">
                {{article.praiseCount}}赞
              </div>
            </div>
            <div @click="modifyRelation(2)">
              <svg v-if="store" style="width: 50%;height:50%" t="1585131167826" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2905" width="200" height="200"><path d="M335.008 916.629333c-35.914667 22.314667-82.88 10.773333-104.693333-25.557333a77.333333 77.333333 0 0 1-8.96-57.429333l46.485333-198.24a13.141333 13.141333 0 0 0-4.021333-12.864l-152.16-132.586667c-31.605333-27.52-35.253333-75.648-8.234667-107.733333a75.68 75.68 0 0 1 51.733333-26.752L354.848 339.2c4.352-0.362667 8.245333-3.232 10.026667-7.594667l76.938666-188.170666c16.032-39.2 60.618667-57.92 99.52-41.461334a76.309333 76.309333 0 0 1 40.832 41.461334l76.938667 188.16c1.781333 4.373333 5.674667 7.253333 10.026667 7.605333l199.712 16.277333c41.877333 3.413333 72.885333 40.458667 69.568 82.517334a76.938667 76.938667 0 0 1-26.08 51.978666l-152.16 132.586667c-3.541333 3.082667-5.141333 8.074667-4.021334 12.853333l46.485334 198.24c9.621333 41.013333-15.36 82.336-56.138667 92.224a75.285333 75.285333 0 0 1-57.525333-9.237333l-170.976-106.24a11.296 11.296 0 0 0-12.010667 0l-170.986667 106.24z" p-id="2906" fill="#d81e06"></path></svg>
              <svg v-else style="width: 50%;height:50%" t="1585136810011" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4741" width="200" height="200"><path d="M335.008 916.629333c-35.914667 22.314667-82.88 10.773333-104.693333-25.557333a77.333333 77.333333 0 0 1-8.96-57.429333l46.485333-198.24a13.141333 13.141333 0 0 0-4.021333-12.864l-152.16-132.586667c-31.605333-27.52-35.253333-75.648-8.234667-107.733333a75.68 75.68 0 0 1 51.733333-26.752L354.848 339.2c4.352-0.362667 8.245333-3.232 10.026667-7.594667l76.938666-188.170666c16.032-39.2 60.618667-57.92 99.52-41.461334a76.309333 76.309333 0 0 1 40.832 41.461334l76.938667 188.16c1.781333 4.373333 5.674667 7.253333 10.026667 7.605333l199.712 16.277333c41.877333 3.413333 72.885333 40.458667 69.568 82.517334a76.938667 76.938667 0 0 1-26.08 51.978666l-152.16 132.586667c-3.541333 3.082667-5.141333 8.074667-4.021334 12.853333l46.485334 198.24c9.621333 41.013333-15.36 82.336-56.138667 92.224a75.285333 75.285333 0 0 1-57.525333-9.237333l-170.976-106.24a11.296 11.296 0 0 0-12.010667 0l-170.986667 106.24zM551.786667 756.032l170.976 106.24c2.624 1.621333 5.717333 2.122667 8.650666 1.408 6.410667-1.557333 10.56-8.426667 8.928-15.424l-46.485333-198.24a77.141333 77.141333 0 0 1 24.277333-75.733333L870.293333 441.706667c2.485333-2.165333 4.053333-5.312 4.330667-8.746667 0.565333-7.136-4.490667-13.173333-10.976-13.696l-199.712-16.288a75.989333 75.989333 0 0 1-64.064-47.168l-76.938667-188.16a12.309333 12.309333 0 0 0-6.538666-6.741333c-5.898667-2.496-12.725333 0.373333-15.328 6.741333l-76.949334 188.16a75.989333 75.989333 0 0 1-64.064 47.168l-199.701333 16.288a11.68 11.68 0 0 0-7.978667 4.181333 13.226667 13.226667 0 0 0 1.333334 18.261334l152.16 132.586666a77.141333 77.141333 0 0 1 24.277333 75.733334l-46.485333 198.229333a13.333333 13.333333 0 0 0 1.514666 9.877333c3.488 5.792 10.581333 7.530667 16.064 4.128l170.986667-106.229333a75.296 75.296 0 0 1 79.562667 0z" p-id="4742" fill="#8a8a8a"></path></svg>
              <div class="word">
                {{article.storeCount}}收藏
              </div>
            </div>
            <div>
              <svg style="width: 50%;height:50%" t="1585196396690" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1502" width="200" height="200"><path d="M512 256a416.853333 416.853333 0 0 1 376.32 234.666667A416.853333 416.853333 0 0 1 512 725.333333a416.853333 416.853333 0 0 1-376.32-234.666666A416.853333 416.853333 0 0 1 512 256m0-85.333333C298.666667 170.666667 116.48 303.36 42.666667 490.666667 116.48 677.973333 298.666667 810.666667 512 810.666667s395.52-132.693333 469.333333-320C907.52 303.36 725.333333 170.666667 512 170.666667z m0 213.333333a106.666667 106.666667 0 0 1 0 213.333333 106.666667 106.666667 0 0 1 0-213.333333m0-85.333333c-105.813333 0-192 86.186667-192 192S406.186667 682.666667 512 682.666667s192-86.186667 192-192S617.813333 298.666667 512 298.666667z" fill="#8a8a8a" p-id="1503"></path></svg>
              <div class="word">
                {{article.visitCount}}浏览
              </div>
            </div>
            <div>
              <svg style="width: 50%;height:50%" t="1585199866320" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3565" width="200" height="200"><path d="M157.568 751.296c-11.008-18.688-18.218667-31.221333-21.802667-37.909333A424.885333 424.885333 0 0 1 85.333333 512C85.333333 276.362667 276.362667 85.333333 512 85.333333s426.666667 191.029333 426.666667 426.666667-191.029333 426.666667-426.666667 426.666667a424.778667 424.778667 0 0 1-219.125333-60.501334 2786.56 2786.56 0 0 0-20.053334-11.765333l-104.405333 28.48c-23.893333 6.506667-45.802667-15.413333-39.285333-39.296l28.437333-104.288z m65.301333 3.786667l-17.258666 63.306666 63.306666-17.258666a32 32 0 0 1 24.522667 3.210666 4515.84 4515.84 0 0 1 32.352 18.944A360.789333 360.789333 0 0 0 512 874.666667c200.298667 0 362.666667-162.368 362.666667-362.666667S712.298667 149.333333 512 149.333333 149.333333 311.701333 149.333333 512c0 60.586667 14.848 118.954667 42.826667 171.136 3.712 6.912 12.928 22.826667 27.370667 47.232a32 32 0 0 1 3.338666 24.714667z m145.994667-70.773334a32 32 0 1 1 40.917333-49.205333A159.189333 159.189333 0 0 0 512 672c37.888 0 73.674667-13.173333 102.186667-36.885333a32 32 0 0 1 40.917333 49.216A223.178667 223.178667 0 0 1 512 736a223.178667 223.178667 0 0 1-143.136-51.690667z" p-id="3566" fill="#8a8a8a"></path></svg>
              <div class="word">
                {{commons.length}}评论
              </div>
            </div>
          </div>
        </el-aside>
    </el-container>
  </div>
</template>
<script>
import mybar from "../mybar"
import {converObjToUrl,baseURL} from '../..//utils/utils'
export default {
  name: 'detail',
  data() {
    return {
      user: {},
      baseUrl: '',
      article: {},
      follow: false,
      praise: false,
      praiseArticle: false,
      store: false,
      fansCount: 0,
      storeCount: 0,
      praiseCount: 0,
      commonContent: '',
      commons: [],
      loadingCommons: false,
      commonsPageCount: 1,
      nomore: false
    }
  },
  methods: {
    //回复评论
    reply(topic) {
      console.log(topic)
      let obj = {};
      obj.type = 1;
      obj.articleId = this.article.id;
      obj.topicId = topic.id;
      obj.parentId = topic.id;
      obj.content = topic.reply;
      obj.toUserId = topic.userId;
      topic.reply = '';
      this.$axios.post("/topic/publishTopic", obj).then(response => {
        //在尾部添加该回复
        let temp = response.data.data;
        let arr = [];
        arr.push(temp);
        topic.replies = topic.replies.concat(arr)
        this.$message.success("回复成功！")
      })
    },
    //点赞评论
    praiseCommon(item) {
      this.modifyRelation(5,item.id, item.praised);
      item.praised = !item.praised;
      if(item.praised) {
        item.praiseCount++;
      } else {
        item.praiseCount--;
      }
    },
    //加载评论
    load() {
      if(!this.nomore) {
        this.loadingCommons = true
        this.getCommons(this.commonsPageCount++)
      }
    },
    //获取评论
    getCommons(count) {
      this.$axios.get('/topic/getCommons', {
        params : {
          articleId : this.article.id,
          pageSize : 10,
          pageNum : count
        }
      }).then(response => {
        this.loadingCommons = false;
        if(response.data.data.length === 0) {
          this.nomore = true;
        } else{
          this.commons = this.commons.concat(response.data.data)
          console.log(this.commons)
        }
      })
    },
    //发表评论
    publishTopic() {
      let obj = {};
      obj.type = 0;
      obj.articleId = this.article.id;
      obj.content = this.commonContent;
      obj.toUserId = this.article.userId;
      this.commonContent = '';
      this.$axios.post("/topic/publishTopic", obj).then(response => {
        //在头部添加该评论
        let temp = response.data.data;
        let arr = [];
        arr.push(temp);
        this.commons = arr.concat(this.commons)
        this.$message.success("发表成功！")
      })
    },
    //获取数目
    getRelatioinsCount() {
      this.$axios.get("/relation/getRelationCount", {
        params : {
          userId : this.article.userId,
          articleId: this.article.id
        }
      }).then(response => {
        this.fansCount = response.data.data.fans;
        this.storeCount = response.data.data.byStore;
        this.praiseCount = response.data.data.byPraise;
      })
    },
    //获取点赞、关注、收藏文章关系
    getRelations() {
      let params = {
              id : this.article.userId,
              articleId: this.article.id
            }
      this.$axios.get("/relation/getRelations" + converObjToUrl(params)).then(response => {
        this.follow = response.data.data.follow;
        this.praise = response.data.data.praise;
        this.store = response.data.data.store;
        this.praiseArticle = response.data.data.praiseArticle
        this.getRelatioinsCount()
      })
    },
    //更改关系状态
    modifyRelation(type, topicId, status) {
      if(type) {
        let ops = 0;
        let flag = false;
        if(type === 1) {
          //点赞用户
          flag = this.praise;
          this.praise = !this.praise;
          if(this.praise) {
            this.praiseCount++;
          } else{
            this.praiseCount--;
          }
        } else if(type === 2) {
          //收藏文章
          flag = this.store;
          this.store = !this.store;
          if(this.store) {
            this.article.storeCount++;
          } else{
            this.article.storeCount--;
          }
        } else if(type === 3) {
          //关注用户
          flag = this.follow;
          this.follow = !this.follow;
          if(this.follow) {
            this.fansCount++;
          } else{
            this.fansCount--;
          }
        } else if(type === 4) {
          //点赞文章
          flag = this.praiseArticle;
          this.praiseArticle = !this.praiseArticle;
          if(this.praiseArticle) {
            this.article.praiseCount++;
          } else{
            this.article.praiseCount--;
          }
        }
        if(flag) {
          //flag 为true 为取消
          ops = 1;
        }
        this.$axios.get("/relation/modifyRelation", {
          params : {
            userId : this.article.userId,
            articleId : this.article.id,
            topicId : topicId,
            type : type,
            ops : ops
          }
        })
      }
    },
    //获取文章
    getArticle(id) {
      if(id) {
        this.$axios.get("/article/detail", {
          params : {
            id : id
          }
        }).then(response => {
          this.article = response.data.data
          //获取关系
          this.getRelations();
        })
      }
    },
    setUser(obj) {
      this.user = obj
    },
  },
  mounted() {
    this.baseUrl = baseURL
    this.getArticle(this.$route.params.id);
  },
  components:{
    'mybar': mybar
  },
}
</script>
<style scoped>
.common-border{
  border-bottom: 1px solid #f5f6f7;
  padding-top: 10px;
  padding-bottom: 10px;
}
.my-border{
  border-bottom: 1px solid #f5f6f7;
  padding-bottom: 10px;
}
.common-reply{
  margin-right: 10px;
}
.common-out{
  display: flex;
}
.common-content{
  display: flex;
  flex-direction: column;
}
.common-content > div{
  margin-bottom: 20px;
}
.common-content-detail{
  display: flex;
  flex-direction: column;
}
.common-bar{
  margin: 10px;
}
.word{
  color: gray
}
.user-common{
  display: flex;
  align-items: center;
  flex: 1 8 1 auto
}
.user-common > div {
  margin-right: 1%
}
.common{
}
.content{
  margin-top: 5%;
}
.title-detail{
  color: #a1a2a2;
  font-size: 8px;
  border-bottom: 1px solid #f5f6f7;
  padding-bottom: 1%;
}
.user-flex{
  display: flex;
  align-items: center;
}
.reply-flex{
  display: flex;
  align-items: center;
}
.reply-flex > div{
  margin-right : 1%
}
.out-container{
  margin: 10%;
}
.out-container > div{
  margin-bottom: 20%;
}
.red{
  background-color: red;
}
.black{
  background-color: black;
}
.user-detail{
  display: flex;
  align-items: center;
}
.user-data{
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.article-data > div{
  margin-left: 40%;
  margin-top: 70%;
}
a{
  text-decoration: none;
  color: balck
}
</style>
