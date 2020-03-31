<template>
  <div>
    <div>
      <mybar ref="bar"></mybar>
    </div>
    <div class="out-container">
      <div class="user-container">
        <div class="user-left">
          <div>
            <el-avatar :size="30">
              <img :src="baseUrl + other.picture"/>
            </el-avatar>
          </div>
          <div >
            <b style="color: black">{{other.userName}}</b>
            <i v-if="other.gender == 0" class="el-icon-male"></i>
            <i v-else class="el-icon-female"></i>
            <span>{{other.age}}</span>
            <div style="color: #969696;fontSize: 4px">
              <span>{{other.school}}</span>
              <span>{{other.work}}</span>
            </div>
          </div>
        </div>
        <div v-if="other.id != user.id">
          <div>
            <el-button type="danger">私信</el-button>
            <el-button @click="follow(other)" v-if="other.follow">取消关注</el-button>
            <el-button @click="follow(other)" type="success" v-else>关注</el-button>
          </div>
        </div>
      </div>
      <div class="kind-container">
        <div>
          <el-menu :default-active="handleNum" class="el-menu-demo" mode="horizontal" @select="handleSelect">
            <el-menu-item index="1">文章</el-menu-item>
            <el-menu-item index="2">回复</el-menu-item>
            <el-menu-item index="3">关注({{follows.length}})</el-menu-item>
            <el-menu-item index="4">粉丝({{fans.length}})</el-menu-item>
            <el-menu-item index="5" v-if="other.id == user.id">我的收藏({{storeArticles.length}})</el-menu-item>
            <el-menu-item index="6" v-if="other.id == user.id">草稿</el-menu-item>
            <el-menu-item index="7">资料</el-menu-item>
          </el-menu>
          <div class="line"></div>
        </div>
        <div>
          <div v-if="handleNum == 1" class="infinite-list-wrapper main-content" style="overflow:auto" >
            <div
              class="list"
              v-infinite-scroll="getArticles" infinite-scroll-disabled="disabled"  style="overflow:auto">
               <div v-for="(item,key) in articles" :key="item.id" class="segment">
                <div class="content">
                  <div class="title" @click="goView(item.id)">
                    <h4>{{item.title}}</h4>
                  </div>
                  <div class="detail">
                    <div class="user myflex">
                      <div>
                        <el-avatar :size="30">
                          <img :src="baseUrl + item.user.picture"/>
                        </el-avatar>
                      </div>
                      <div style="margin-left: 15%">
                        <span>{{item.user.userName}}</span>
                      </div>
                    </div>
                    <div class="myflex">
                      <div>
                        <svg style="width: 20px;height:20px" t="1585199866320" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3565" width="200" height="200"><path d="M157.568 751.296c-11.008-18.688-18.218667-31.221333-21.802667-37.909333A424.885333 424.885333 0 0 1 85.333333 512C85.333333 276.362667 276.362667 85.333333 512 85.333333s426.666667 191.029333 426.666667 426.666667-191.029333 426.666667-426.666667 426.666667a424.778667 424.778667 0 0 1-219.125333-60.501334 2786.56 2786.56 0 0 0-20.053334-11.765333l-104.405333 28.48c-23.893333 6.506667-45.802667-15.413333-39.285333-39.296l28.437333-104.288z m65.301333 3.786667l-17.258666 63.306666 63.306666-17.258666a32 32 0 0 1 24.522667 3.210666 4515.84 4515.84 0 0 1 32.352 18.944A360.789333 360.789333 0 0 0 512 874.666667c200.298667 0 362.666667-162.368 362.666667-362.666667S712.298667 149.333333 512 149.333333 149.333333 311.701333 149.333333 512c0 60.586667 14.848 118.954667 42.826667 171.136 3.712 6.912 12.928 22.826667 27.370667 47.232a32 32 0 0 1 3.338666 24.714667z m145.994667-70.773334a32 32 0 1 1 40.917333-49.205333A159.189333 159.189333 0 0 0 512 672c37.888 0 73.674667-13.173333 102.186667-36.885333a32 32 0 0 1 40.917333 49.216A223.178667 223.178667 0 0 1 512 736a223.178667 223.178667 0 0 1-143.136-51.690667z" p-id="3566" fill="#8a8a8a"></path></svg>
                        <span>{{item.topicCount}}&nbsp;&nbsp;</span>
                      </div>
                      <div>
                        <svg style="width: 20px;height:20px" t="1585136810011" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4741" width="200" height="200"><path d="M335.008 916.629333c-35.914667 22.314667-82.88 10.773333-104.693333-25.557333a77.333333 77.333333 0 0 1-8.96-57.429333l46.485333-198.24a13.141333 13.141333 0 0 0-4.021333-12.864l-152.16-132.586667c-31.605333-27.52-35.253333-75.648-8.234667-107.733333a75.68 75.68 0 0 1 51.733333-26.752L354.848 339.2c4.352-0.362667 8.245333-3.232 10.026667-7.594667l76.938666-188.170666c16.032-39.2 60.618667-57.92 99.52-41.461334a76.309333 76.309333 0 0 1 40.832 41.461334l76.938667 188.16c1.781333 4.373333 5.674667 7.253333 10.026667 7.605333l199.712 16.277333c41.877333 3.413333 72.885333 40.458667 69.568 82.517334a76.938667 76.938667 0 0 1-26.08 51.978666l-152.16 132.586667c-3.541333 3.082667-5.141333 8.074667-4.021334 12.853333l46.485334 198.24c9.621333 41.013333-15.36 82.336-56.138667 92.224a75.285333 75.285333 0 0 1-57.525333-9.237333l-170.976-106.24a11.296 11.296 0 0 0-12.010667 0l-170.986667 106.24zM551.786667 756.032l170.976 106.24c2.624 1.621333 5.717333 2.122667 8.650666 1.408 6.410667-1.557333 10.56-8.426667 8.928-15.424l-46.485333-198.24a77.141333 77.141333 0 0 1 24.277333-75.733333L870.293333 441.706667c2.485333-2.165333 4.053333-5.312 4.330667-8.746667 0.565333-7.136-4.490667-13.173333-10.976-13.696l-199.712-16.288a75.989333 75.989333 0 0 1-64.064-47.168l-76.938667-188.16a12.309333 12.309333 0 0 0-6.538666-6.741333c-5.898667-2.496-12.725333 0.373333-15.328 6.741333l-76.949334 188.16a75.989333 75.989333 0 0 1-64.064 47.168l-199.701333 16.288a11.68 11.68 0 0 0-7.978667 4.181333 13.226667 13.226667 0 0 0 1.333334 18.261334l152.16 132.586666a77.141333 77.141333 0 0 1 24.277333 75.733334l-46.485333 198.229333a13.333333 13.333333 0 0 0 1.514666 9.877333c3.488 5.792 10.581333 7.530667 16.064 4.128l170.986667-106.229333a75.296 75.296 0 0 1 79.562667 0z" p-id="4742" fill="#8a8a8a"></path></svg>
                        <span>{{item.storeCount}}&nbsp;&nbsp;</span>
                      </div>
                      <div>
                        <svg style="width: 20px;height:20px" t="1585196396690" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1502" width="200" height="200"><path d="M512 256a416.853333 416.853333 0 0 1 376.32 234.666667A416.853333 416.853333 0 0 1 512 725.333333a416.853333 416.853333 0 0 1-376.32-234.666666A416.853333 416.853333 0 0 1 512 256m0-85.333333C298.666667 170.666667 116.48 303.36 42.666667 490.666667 116.48 677.973333 298.666667 810.666667 512 810.666667s395.52-132.693333 469.333333-320C907.52 303.36 725.333333 170.666667 512 170.666667z m0 213.333333a106.666667 106.666667 0 0 1 0 213.333333 106.666667 106.666667 0 0 1 0-213.333333m0-85.333333c-105.813333 0-192 86.186667-192 192S406.186667 682.666667 512 682.666667s192-86.186667 192-192S617.813333 298.666667 512 298.666667z" fill="#8a8a8a" p-id="1503"></path></svg>
                        <span>{{item.visitCount}}&nbsp;&nbsp;</span>
                      </div>
                      <div>
                        <svg style="width: 20px;height:20px" t="1585199681019" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3235" width="200" height="200"><path d="M621.674667 408.021333c16.618667-74.24 28.224-127.936 34.837333-161.194666C673.152 163.093333 629.941333 85.333333 544.298667 85.333333c-77.226667 0-116.010667 38.378667-138.88 115.093334l-0.586667 2.24c-13.728 62.058667-34.72 110.165333-62.506667 144.586666a158.261333 158.261333 0 0 1-119.733333 58.965334l-21.909333 0.469333C148.437333 407.808 106.666667 450.816 106.666667 503.498667V821.333333c0 64.8 52.106667 117.333333 116.394666 117.333334h412.522667c84.736 0 160.373333-53.568 189.12-133.92l85.696-239.584c21.802667-60.96-9.536-128.202667-70.005333-150.186667a115.552 115.552 0 0 0-39.488-6.954667H621.674667zM544.256 149.333333c39.253333 0 59.498667 36.48 49.888 84.928-7.573333 38.144-21.984 104.426667-43.221333 198.666667-4.512 20.021333 10.56 39.093333 30.912 39.093333h218.666666c6.101333 0 12.16 1.066667 17.909334 3.168 27.445333 9.984 41.674667 40.554667 31.776 68.266667l-85.568 239.573333C744.981333 838.026667 693.301333 874.666667 635.402667 874.666667H223.498667C194.314667 874.666667 170.666667 850.784 170.666667 821.333333V503.498667c0-17.866667 14.144-32.448 31.829333-32.821334l21.866667-0.469333a221.12 221.12 0 0 0 167.381333-82.56c34.346667-42.602667 59.146667-99.306667 74.869333-169.877333C482.101333 166.336 499.552 149.333333 544.266667 149.333333z" p-id="3236" fill="#8a8a8a"></path></svg>
                        <span>{{item.praiseCount}}&nbsp;&nbsp;</span>
                      </div>
                      <div v-if="other.id == user.id">
                        <span>
                          <svg style="width: 20px;height:20px" t="1585368044617" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2623" width="200" height="200"><path d="M202.666667 256h-42.666667a32 32 0 0 1 0-64h704a32 32 0 0 1 0 64H266.666667v565.333333a53.333333 53.333333 0 0 0 53.333333 53.333334h384a53.333333 53.333333 0 0 0 53.333333-53.333334V352a32 32 0 0 1 64 0v469.333333c0 64.8-52.533333 117.333333-117.333333 117.333334H320c-64.8 0-117.333333-52.533333-117.333333-117.333334V256z m224-106.666667a32 32 0 0 1 0-64h170.666666a32 32 0 0 1 0 64H426.666667z m-32 288a32 32 0 0 1 64 0v256a32 32 0 0 1-64 0V437.333333z m170.666666 0a32 32 0 0 1 64 0v256a32 32 0 0 1-64 0V437.333333z" p-id="2624" fill="#d81e06"></path></svg>
                          <span @click="deleteArticle(item.id,key)" class="delteOrCancel" >删除</span>
                       </span>
                      </div>
                       <div v-if="other.id == user.id">
                        <span>
                          <svg style="width: 20px;height:20px" t="1585455562241" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2623" width="200" height="200"><path d="M862.709333 116.042667a32 32 0 1 1 45.248 45.248L455.445333 613.813333a32 32 0 1 1-45.258666-45.258666L862.709333 116.053333zM853.333333 448a32 32 0 0 1 64 0v352c0 64.8-52.533333 117.333333-117.333333 117.333333H224c-64.8 0-117.333333-52.533333-117.333333-117.333333V224c0-64.8 52.533333-117.333333 117.333333-117.333333h341.333333a32 32 0 0 1 0 64H224a53.333333 53.333333 0 0 0-53.333333 53.333333v576a53.333333 53.333333 0 0 0 53.333333 53.333333h576a53.333333 53.333333 0 0 0 53.333333-53.333333V448z" p-id="2624" fill="#2c2c2c"></path></svg>
                          <span @click="goArticle(item.id)" class="delteOrCancel" >编辑</span>
                       </span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <p style="text-align: center;color: #8a8a8a;fontSize: 4px" v-if="articleMore && articleLoading">加载中...</p>
            <p style="text-align: center;color: #8a8a8a;fontSize: 4px" v-if="!articleMore">没有更多了...</p>
            </div>
        </div>
        <div >
          <div v-if="handleNum == 2" class="infinite-list-wrapper main-content" style="overflow:auto" >
            <div
              class="list"
              v-infinite-scroll="getTopics" infinite-scroll-disabled="topicDisabled"  style="overflow:auto">
               <div v-for="(item,key) in topics" :key="item.id" class="segment">
                <div class="content" >
                  <div class="title" >
                    <h4><span style="color: blue;margin-right: 10px">@{{item.user.userName}}</span>{{item.content}}</h4>
                  </div>
                  <div class="detail">
                    <div class="user myflex">
                      <div>
                        <el-avatar :size="30">
                          <img :src="baseUrl + item.user.picture"/>
                        </el-avatar>
                      </div>
                      <div style="margin-left: 15%">
                        <span>{{item.user.userName}}</span>
                      </div>
                    </div>
                    <div class="myflex">
                      <span style="fontSize: 8px">{{item.updateTime}}</span>
                      <span style="margin-left: 30px" >
                          <span v-if="other.id == item.userId">
                            <svg style="width: 20px;height:20px" t="1585368044617" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2623" width="200" height="200"><path d="M202.666667 256h-42.666667a32 32 0 0 1 0-64h704a32 32 0 0 1 0 64H266.666667v565.333333a53.333333 53.333333 0 0 0 53.333333 53.333334h384a53.333333 53.333333 0 0 0 53.333333-53.333334V352a32 32 0 0 1 64 0v469.333333c0 64.8-52.533333 117.333333-117.333333 117.333334H320c-64.8 0-117.333333-52.533333-117.333333-117.333334V256z m224-106.666667a32 32 0 0 1 0-64h170.666666a32 32 0 0 1 0 64H426.666667z m-32 288a32 32 0 0 1 64 0v256a32 32 0 0 1-64 0V437.333333z m170.666666 0a32 32 0 0 1 64 0v256a32 32 0 0 1-64 0V437.333333z" p-id="2624" fill="#d81e06"></path></svg>
                            <span @click="deleteCommon(item.id,key)" style="color: #969696;fontSize:50%;margin-right: 10px">删除</span>
                          </span>
                          <span @click="goView(item.articleId)" style="color: #969696;fontSize:50%"><a>查看详情</a></span>
                       </span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <p style="text-align: center;color: #8a8a8a;fontSize: 4px" v-if="topicMore && topicLoading">加载中...</p>
            <p style="text-align: center;color: #8a8a8a;fontSize: 4px" v-if="!topicMore">没有更多了...</p>
            </div>
        </div>
        <div>
          <div v-if="handleNum == 3">
            <div v-for="item in follows" :key="item.id">
              <div class="follow-container">
                <div class="user-left">
                  <div @click="goOwn(item.id)">
                    <el-avatar :size="30">
                      <img :src="baseUrl + item.picture"/>
                    </el-avatar>
                  </div>
                  <div @click="goOwn(item.id)">
                    <b style="color: black">{{item.userName}}</b>
                    <i v-if="item.gender == 0" class="el-icon-male"></i>
                    <i v-else class="el-icon-female"></i>
                    <span>{{item.age}}</span>
                    <div style="color: #969696;fontSize: 4px">
                      <span>{{item.school}}</span>
                      <span>{{item.work}}</span>
                    </div>
                  </div>
                </div>
                <div>
                  <div >
                    <el-button @click="follow(item)" v-if="item.follow">取消关注</el-button>
                    <el-button @click="follow(item)" type="success" v-else>关注</el-button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
         <div>
          <div v-if="handleNum == 4">
            <div v-for="item in fans" :key="item.id">
              <div class="follow-container">
                <div class="user-left">
                  <div @click="goOwn(item.id)">
                    <el-avatar :size="30">
                      <img :src="baseUrl + item.picture"/>
                    </el-avatar>
                  </div>
                  <div @click="goOwn(item.id)">
                    <b style="color: black">{{item.userName}}</b>
                    <i v-if="item.gender == 0" class="el-icon-male"></i>
                    <i v-else class="el-icon-female"></i>
                    <span>{{item.age}}</span>
                    <div style="color: #969696;fontSize: 4px">
                      <span>{{item.school}}</span>
                      <span>{{item.work}}</span>
                    </div>
                  </div>
                </div>
                <div>
                  <div >
                    <el-button @click="follow(item)" v-if="item.follow">取消关注</el-button>
                    <el-button @click="follow(item)" type="success" v-else>关注</el-button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div>
          <div v-if="handleNum == 5" class="infinite-list-wrapper main-content" style="overflow:auto" >
            <div
              class="list"
              v-infinite-scroll="getStoreArticles" infinite-scroll-disabled="disabled"  style="overflow:auto">
               <div v-for="(item,key) in storeArticles" :key="item.id" class="segment">
                <div class="content" >
                  <div class="title" @click="goView(item.id)">
                    <h4>{{item.title}}</h4>
                  </div>
                  <div class="detail">
                    <div class="user myflex">
                      <div>
                        <el-avatar :size="30">
                          <img :src="baseUrl + item.user.picture"/>
                        </el-avatar>
                      </div>
                      <div style="margin-left: 15%">
                        <span>{{item.user.userName}}</span>
                      </div>
                    </div>
                    <div class="myflex">
                      <div>
                        <svg style="width: 20px;height:20px" t="1585199866320" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3565" width="200" height="200"><path d="M157.568 751.296c-11.008-18.688-18.218667-31.221333-21.802667-37.909333A424.885333 424.885333 0 0 1 85.333333 512C85.333333 276.362667 276.362667 85.333333 512 85.333333s426.666667 191.029333 426.666667 426.666667-191.029333 426.666667-426.666667 426.666667a424.778667 424.778667 0 0 1-219.125333-60.501334 2786.56 2786.56 0 0 0-20.053334-11.765333l-104.405333 28.48c-23.893333 6.506667-45.802667-15.413333-39.285333-39.296l28.437333-104.288z m65.301333 3.786667l-17.258666 63.306666 63.306666-17.258666a32 32 0 0 1 24.522667 3.210666 4515.84 4515.84 0 0 1 32.352 18.944A360.789333 360.789333 0 0 0 512 874.666667c200.298667 0 362.666667-162.368 362.666667-362.666667S712.298667 149.333333 512 149.333333 149.333333 311.701333 149.333333 512c0 60.586667 14.848 118.954667 42.826667 171.136 3.712 6.912 12.928 22.826667 27.370667 47.232a32 32 0 0 1 3.338666 24.714667z m145.994667-70.773334a32 32 0 1 1 40.917333-49.205333A159.189333 159.189333 0 0 0 512 672c37.888 0 73.674667-13.173333 102.186667-36.885333a32 32 0 0 1 40.917333 49.216A223.178667 223.178667 0 0 1 512 736a223.178667 223.178667 0 0 1-143.136-51.690667z" p-id="3566" fill="#8a8a8a"></path></svg>
                        <span>{{item.topicCount}}&nbsp;&nbsp;</span>
                      </div>
                      <div>
                        <svg style="width: 20px;height:20px" t="1585136810011" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4741" width="200" height="200"><path d="M335.008 916.629333c-35.914667 22.314667-82.88 10.773333-104.693333-25.557333a77.333333 77.333333 0 0 1-8.96-57.429333l46.485333-198.24a13.141333 13.141333 0 0 0-4.021333-12.864l-152.16-132.586667c-31.605333-27.52-35.253333-75.648-8.234667-107.733333a75.68 75.68 0 0 1 51.733333-26.752L354.848 339.2c4.352-0.362667 8.245333-3.232 10.026667-7.594667l76.938666-188.170666c16.032-39.2 60.618667-57.92 99.52-41.461334a76.309333 76.309333 0 0 1 40.832 41.461334l76.938667 188.16c1.781333 4.373333 5.674667 7.253333 10.026667 7.605333l199.712 16.277333c41.877333 3.413333 72.885333 40.458667 69.568 82.517334a76.938667 76.938667 0 0 1-26.08 51.978666l-152.16 132.586667c-3.541333 3.082667-5.141333 8.074667-4.021334 12.853333l46.485334 198.24c9.621333 41.013333-15.36 82.336-56.138667 92.224a75.285333 75.285333 0 0 1-57.525333-9.237333l-170.976-106.24a11.296 11.296 0 0 0-12.010667 0l-170.986667 106.24zM551.786667 756.032l170.976 106.24c2.624 1.621333 5.717333 2.122667 8.650666 1.408 6.410667-1.557333 10.56-8.426667 8.928-15.424l-46.485333-198.24a77.141333 77.141333 0 0 1 24.277333-75.733333L870.293333 441.706667c2.485333-2.165333 4.053333-5.312 4.330667-8.746667 0.565333-7.136-4.490667-13.173333-10.976-13.696l-199.712-16.288a75.989333 75.989333 0 0 1-64.064-47.168l-76.938667-188.16a12.309333 12.309333 0 0 0-6.538666-6.741333c-5.898667-2.496-12.725333 0.373333-15.328 6.741333l-76.949334 188.16a75.989333 75.989333 0 0 1-64.064 47.168l-199.701333 16.288a11.68 11.68 0 0 0-7.978667 4.181333 13.226667 13.226667 0 0 0 1.333334 18.261334l152.16 132.586666a77.141333 77.141333 0 0 1 24.277333 75.733334l-46.485333 198.229333a13.333333 13.333333 0 0 0 1.514666 9.877333c3.488 5.792 10.581333 7.530667 16.064 4.128l170.986667-106.229333a75.296 75.296 0 0 1 79.562667 0z" p-id="4742" fill="#8a8a8a"></path></svg>
                        <span>{{item.storeCount}}&nbsp;&nbsp;</span>
                      </div>
                      <div>
                        <svg style="width: 20px;height:20px" t="1585196396690" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1502" width="200" height="200"><path d="M512 256a416.853333 416.853333 0 0 1 376.32 234.666667A416.853333 416.853333 0 0 1 512 725.333333a416.853333 416.853333 0 0 1-376.32-234.666666A416.853333 416.853333 0 0 1 512 256m0-85.333333C298.666667 170.666667 116.48 303.36 42.666667 490.666667 116.48 677.973333 298.666667 810.666667 512 810.666667s395.52-132.693333 469.333333-320C907.52 303.36 725.333333 170.666667 512 170.666667z m0 213.333333a106.666667 106.666667 0 0 1 0 213.333333 106.666667 106.666667 0 0 1 0-213.333333m0-85.333333c-105.813333 0-192 86.186667-192 192S406.186667 682.666667 512 682.666667s192-86.186667 192-192S617.813333 298.666667 512 298.666667z" fill="#8a8a8a" p-id="1503"></path></svg>
                        <span>{{item.visitCount}}&nbsp;&nbsp;</span>
                      </div>
                      <div>
                        <svg style="width: 20px;height:20px" t="1585199681019" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3235" width="200" height="200"><path d="M621.674667 408.021333c16.618667-74.24 28.224-127.936 34.837333-161.194666C673.152 163.093333 629.941333 85.333333 544.298667 85.333333c-77.226667 0-116.010667 38.378667-138.88 115.093334l-0.586667 2.24c-13.728 62.058667-34.72 110.165333-62.506667 144.586666a158.261333 158.261333 0 0 1-119.733333 58.965334l-21.909333 0.469333C148.437333 407.808 106.666667 450.816 106.666667 503.498667V821.333333c0 64.8 52.106667 117.333333 116.394666 117.333334h412.522667c84.736 0 160.373333-53.568 189.12-133.92l85.696-239.584c21.802667-60.96-9.536-128.202667-70.005333-150.186667a115.552 115.552 0 0 0-39.488-6.954667H621.674667zM544.256 149.333333c39.253333 0 59.498667 36.48 49.888 84.928-7.573333 38.144-21.984 104.426667-43.221333 198.666667-4.512 20.021333 10.56 39.093333 30.912 39.093333h218.666666c6.101333 0 12.16 1.066667 17.909334 3.168 27.445333 9.984 41.674667 40.554667 31.776 68.266667l-85.568 239.573333C744.981333 838.026667 693.301333 874.666667 635.402667 874.666667H223.498667C194.314667 874.666667 170.666667 850.784 170.666667 821.333333V503.498667c0-17.866667 14.144-32.448 31.829333-32.821334l21.866667-0.469333a221.12 221.12 0 0 0 167.381333-82.56c34.346667-42.602667 59.146667-99.306667 74.869333-169.877333C482.101333 166.336 499.552 149.333333 544.266667 149.333333z" p-id="3236" fill="#8a8a8a"></path></svg>
                        <span>{{item.praiseCount}}&nbsp;&nbsp;</span>
                      </div>
                      <div>
                        <span>
                          <svg style="width: 20px;height:20px" t="1585368044617" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2623" width="200" height="200"><path d="M202.666667 256h-42.666667a32 32 0 0 1 0-64h704a32 32 0 0 1 0 64H266.666667v565.333333a53.333333 53.333333 0 0 0 53.333333 53.333334h384a53.333333 53.333333 0 0 0 53.333333-53.333334V352a32 32 0 0 1 64 0v469.333333c0 64.8-52.533333 117.333333-117.333333 117.333334H320c-64.8 0-117.333333-52.533333-117.333333-117.333334V256z m224-106.666667a32 32 0 0 1 0-64h170.666666a32 32 0 0 1 0 64H426.666667z m-32 288a32 32 0 0 1 64 0v256a32 32 0 0 1-64 0V437.333333z m170.666666 0a32 32 0 0 1 64 0v256a32 32 0 0 1-64 0V437.333333z" p-id="2624" fill="#d81e06"></path></svg>
                          <span @click="cancelStore(item.id,key)" class="delteOrCancel">取消收藏</span>
                       </span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <p style="text-align: center;color: #8a8a8a;fontSize: 4px" v-if="storeArticleMore && storeArticleLoading">加载中...</p>
            <p style="text-align: center;color: #8a8a8a;fontSize: 4px" v-if="!storeArticleMore">没有更多了...</p>
            </div>
        </div>
        <div>
          <div v-if="handleNum == 6" class="infinite-list-wrapper main-content" style="overflow:auto" >
            <div
              class="list"
              v-infinite-scroll="getDraftArticles" infinite-scroll-disabled="draftDisabled"  style="overflow:auto">
               <div v-for="(item,key) in draftArticles" :key="item.id" class="segment">
                <div class="content">
                  <div class="title">
                    <h4>{{item.title}}</h4>
                  </div>
                  <div class="detail">
                    <div class="user myflex">
                      <div>
                        <el-avatar :size="30">
                          <img :src="baseUrl + item.user.picture"/>
                        </el-avatar>
                      </div>
                      <div style="margin-left: 15%">
                        <span>{{item.user.userName}}</span>
                      </div>
                    </div>
                    <div class="myflex">

                      <div v-if="other.id == user.id">
                        <span>
                          <svg style="width: 20px;height:20px" t="1585368044617" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2623" width="200" height="200"><path d="M202.666667 256h-42.666667a32 32 0 0 1 0-64h704a32 32 0 0 1 0 64H266.666667v565.333333a53.333333 53.333333 0 0 0 53.333333 53.333334h384a53.333333 53.333333 0 0 0 53.333333-53.333334V352a32 32 0 0 1 64 0v469.333333c0 64.8-52.533333 117.333333-117.333333 117.333334H320c-64.8 0-117.333333-52.533333-117.333333-117.333334V256z m224-106.666667a32 32 0 0 1 0-64h170.666666a32 32 0 0 1 0 64H426.666667z m-32 288a32 32 0 0 1 64 0v256a32 32 0 0 1-64 0V437.333333z m170.666666 0a32 32 0 0 1 64 0v256a32 32 0 0 1-64 0V437.333333z" p-id="2624" fill="#d81e06"></path></svg>
                          <span @click="deleteArticle(item.id,key)" class="delteOrCancel" >删除</span>
                       </span>
                      </div>
                       <div v-if="other.id == user.id">
                        <span>
                          <svg style="width: 20px;height:20px" t="1585455562241" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2623" width="200" height="200"><path d="M862.709333 116.042667a32 32 0 1 1 45.248 45.248L455.445333 613.813333a32 32 0 1 1-45.258666-45.258666L862.709333 116.053333zM853.333333 448a32 32 0 0 1 64 0v352c0 64.8-52.533333 117.333333-117.333333 117.333333H224c-64.8 0-117.333333-52.533333-117.333333-117.333333V224c0-64.8 52.533333-117.333333 117.333333-117.333333h341.333333a32 32 0 0 1 0 64H224a53.333333 53.333333 0 0 0-53.333333 53.333333v576a53.333333 53.333333 0 0 0 53.333333 53.333333h576a53.333333 53.333333 0 0 0 53.333333-53.333333V448z" p-id="2624" fill="#2c2c2c"></path></svg>
                          <span @click="goArticle(item.id)" class="delteOrCancel" >编辑</span>
                       </span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <p style="text-align: center;color: #8a8a8a;fontSize: 4px" v-if="draftArticleMore && draftArticleLoading">加载中...</p>
            <p style="text-align: center;color: #8a8a8a;fontSize: 4px" v-if="!draftArticleMore">没有更多了...</p>
            </div>
        </div>
        <div>
          <div v-if="handleNum == 7">
            <div class="ducument-container" v-if="user.id == other.id">
              <el-form ref="tempUser" :rules="rules" :model="tempUser" label-width="80px" class="form-data">
                <el-form-item>
                  <div class="bar-picture">
                    <div>
                      <el-upload
                        id="fileInput"
                        class="avatar-uploader"
                        :action="doUpload"
                        :headers="headers"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload">
                        <div class="circular--landscape">
                          <el-avatar  v-if="tempUser.picture" :size="60">
                            <img :src="baseUrl + tempUser.picture"/>
                          </el-avatar>
                          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </div>
                        <el-button size="small" type="success" v-if="!isUpload">更换头像</el-button>
                        <span v-else>上传中……</span>
                      </el-upload>
                    </div>
                  </div>
                </el-form-item>
                <el-form-item label="昵称" prop="userName">
                  <el-input v-model="tempUser.userName" maxlength="20"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                  <el-radio-group v-model="tempUser.sex">
                    <el-radio label="男"></el-radio>
                    <el-radio label="女"></el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="简介">
                  <el-input type="textarea" v-model="tempUser.introduction" maxlength="30"></el-input>
                </el-form-item>
                <el-form-item label="年龄">
                  <el-input v-model="tempUser.age"></el-input>
                </el-form-item>
                <el-form-item label="学校">
                  <el-input v-model="tempUser.school" maxlength="30"></el-input>
                </el-form-item>
                <el-form-item label="地址">
                  <el-input v-model="tempUser.address" maxlength="50"></el-input>
                </el-form-item>
                <el-form-item label="工作">
                  <el-input v-model="tempUser.work" maxlength="30"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click.stop="onSubmit('tempUser')">保存</el-button>
                </el-form-item>
              </el-form>
            </div>
            <div v-else>
             <div class="ducument-container">
              <el-form ref="tempUser" :rules="rules" :model="tempUser" label-width="80px" class="form-data">
                <el-form-item label="昵称:" prop="userName">
                  <span>{{tempUser.userName}}</span>
                </el-form-item>
                <el-form-item label="性别:">
                  <el-radio-group v-model="tempUser.sex">
                    <el-radio label="男"></el-radio>
                    <el-radio label="女"></el-radio>
                  </el-radio-group>
                  <span>{{tempUser.userName}}</span>
                </el-form-item>
                <el-form-item label="简介:">
                  <span>{{tempUser.introduction}}</span>
                </el-form-item>
                <el-form-item label="年龄:">
                  <span>{{tempUser.age}}</span>
                </el-form-item>
                <el-form-item label="学校:">
                  <span>{{tempUser.school}}</span>
                </el-form-item>
                <el-form-item label="地址:">
                  <span>{{tempUser.address}}</span>
                </el-form-item>
                <el-form-item label="工作:">
                  <span>{{tempUser.work}}</span>
                </el-form-item>
              </el-form>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {baseURL,getStore,removeStore,converObjToUrl, setStore} from '../../utils/utils'
import mybar from '../mybar'
export default {
  name : 'ownHome',
  data() {
    var validateUserName = (rule, value, callback) => {
      let reg = /^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/
        if (value === '') {
          callback(new Error('请输入昵称'));
        } else if (reg.test(value)) {
          callback(new Error('用户名不能是邮箱格式'));
        } else if (this.tempName === this.user.userName) {
          callback();
        } else {
          this.$refs.bar.checkAccountUnique(value, 1).then(response => {
            if (!response.data.data) {
              callback('该昵称已被使用');
            } else {
              callback();
            }
          })
        }
      };
    return {
      isUpload: false,
      headers: {},
      rules: {
          userName: [
            { validator: validateUserName, trigger: 'blur' }
          ]
        },
      tempName: '',
      //编辑的用户
      tempUser: {
          userName: '',
          introduction: '',
          age: '',
          sex: '',
          school: '',
          work: '',
          address: '',
          picture: '',
          visitCount: '',
        },
      //上传图片地址
      doUpload:'/file/uploadBar',
      user: {},
      other: {},
      baseUrl: '',
      handleNum: "1",
      articles: [],
      articlePageNum: 1,
      articleMore: true,
      articleLoading: true,
      topics: [],
      topicPageNum: 1,
      topicMore: true,
      topicLoading: true,
      follows: [],
      fans: [],
      storeArticles: [],
      storeArticlePageNum: 1,
      storeArticleMore: true,
      storeArticleLoading: true,
      draftArticles: [],
      draftArticlePageNum: 1,
      draftArticleMore: true,
      draftArticleLoading: true,
    }
  },
  methods: {
    //提交修改信息
    onSubmit(tempUser) {
      this.$refs[tempUser].validate((valid) => {
          if (valid) {
            this.tempUser.ops = 1
            this.$axios.post("/user/updateOrInsert", this.tempUser).then(response => {
              setStore('token', response.data.data)
              this.$refs.bar.getUser();
              this.$message({
                message: '保存成功',
                type: 'success'
              });
            })
          } else {
            return false;
          }
      });
    },
    //上传成功
    handleAvatarSuccess(res, file) {
      this.isUpload = false
      this.getOther()
      this.$refs.bar.getUser();
      this.$message({
          message: '上传成功',
          type: 'success'
      });
    },
    //上传之前检测文件格式
    beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
          return false;
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
          return false;
        }
        this.isUpload = true;
        return isJPG && isLt2M;
    },
    //编辑文章页
    goArticle(id) {
      this.$router.push({path: `/customer/write/${id}`})
    },
    //取消收藏
    cancelStore(id, key) {
      this.$confirm('确定取消收藏吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.get("/relation/modifyRelation", {
            params : {
              articleId : id,
              ops : 1,
              type : 2
            }
          }).then(response => {
            this.storeArticles.splice(key, 1)
          })
        }).catch(() => {
        });
    },
    //删除文章
    deleteArticle(id, key) {
      this.$confirm('确定删除该文章吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get("/article/deleteArticle", {
          params : {
            id : id
          }
        }).then(response => {
          this.articles.splice(key, 1)
          this.$message.success("删除成功！")
        })
      }).catch(() => {
      });
    },
    //跳转用户
    goOwn(id) {
      this.$router.push({path: `/own/ownHome/${id}`})
      history.go(0)
    },
    //关注、取消关注
    follow(obj) {
      let userId = obj.id
      let ops = 1;
      if(!obj.follow) {
        ops = 0;
      }
      this.$axios.get("/relation/modifyRelation", {
          params : {
            userId : userId,
            type : 3,
            ops : ops
          }
        }).then(response => {
          if(response.data.success) {
            obj.follow = !obj.follow;
          }
        })
    },
    //获取所有粉丝和关注
    getFollowsOrFans(type) {
      this.$axios.get("/relation/getFansOrFollows", {
        params : {
          userId : this.$route.params.id,
          type : type
        }
      }).then(response => {
        if(type == 1) {
          this.fans = response.data.data;
        } else if(type == 0) {
          this.follows = response.data.data;
        }
      })
    },
    //删除评论
    deleteCommon(id,key) {
      this.$confirm('确定删除该回复吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.get("/topic/delete", {
            params : {
              topicId : id
            }
          }).then(response => {
              this.topics.splice(key, 1)
              this.$message({
              type: 'success',
              message: '删除成功!'
            });
          })
        }).catch(() => {
        });
    },
    //文章路由
    goView(id) {
      this.$router.push({path: `/customer/detail/${id}`})
    },
    //获取草稿
    getDraftArticles() {
      if(this.draftArticleMore) {
        this.draftArticleLoading = true;
        let article = {};
        article.pageNum = this.draftArticlePageNum;
        article.pageSize = 10;
        article.userId = this.$route.params.id;
        article.type = 0;
        this.draftArticlePageNum++;
        this.$axios.get("/article/getArticles" + converObjToUrl(article)).then(response => {
          this.articleLoading = false
          if(response.data.data.length > 0) {
            this.draftArticles = this.draftArticles.concat(response.data.data);
          } else{
            this.draftArticleMore = false;
          }
        })
      }
    },
    //获取所有文章
    getArticles() {
      if(this.articleMore) {
        this.articleLoading = true;
        let article = {};
        article.pageNum = this.articlePageNum;
        article.pageSize = 10;
        article.userId = this.$route.params.id;
        this.articlePageNum += 1;
        this.$axios.get("/article/getArticles" + converObjToUrl(article)).then(response => {
          this.articleLoading = false
          if(response.data.data.length > 0) {
            this.articles = this.articles.concat(response.data.data);
          } else{
            this.articleMore = false;
          }
        })
      }
    },
    //获取所有文章
    getStoreArticles() {
      if(this.storeArticleMore) {
        this.storeArticleLoading = true;
        this.storeArticlePageNum++;
        this.$axios.get("/article/getStoreArticles").then(response => {
          this.storeArticleLoading = false
          if(response.data.data.length > 0) {
            this.storeArticles = this.storeArticles.concat(response.data.data);
          } else{
            this.storeArticleMore = false;
          }
        })
      }
    },
    //获取topic
    getTopics() {
      if(this.topicMore) {
        this.topicLoading = true;
        let topic = {};
        topic.pageNum = this.topicPageNum;
        topic.pageSize = 10;
        topic.userId = this.$route.params.id;
        this.topicPageNum ++;
        console.log(topic)
        this.$axios.get("/topic/getUserTopics" + converObjToUrl(topic)).then(response => {
          this.topicLoading = false
          if(response.data.data.length > 0) {
            this.topics = this.topics.concat(response.data.data);
            console.log(this.topics)
          } else{
            this.topicMore = false;
          }
        })
      }
    },
    //获取当前浏览的用户信息
    getOther() {
      this.$axios.get("/user/getOther", {
            params : {
              id : this.$route.params.id
            }
          }).then(response => {
          this.other = response.data.data;
          // 浏览他人资料赋值
          this.tempUser = this.other
      })
    },
    setUser(obj) {
      if(this.user.id == this.other.id) {
        this.other = this.user;
      }
      this.user = obj
      this.tempUser = this.other
    },
    handleSelect(key, keyPath) {
      this.handleNum = key;
    }
  },
  mounted() {
    this.baseUrl = baseURL;
    this.getOther()
    this.getFollowsOrFans(0);
    this.getFollowsOrFans(1);
    if(this.user.id) {
      this.getStoreArticles();
    }
    //设置上传接口
    this.doUpload = this.baseUrl + this.doUpload
    let token = getStore("token")
    //为上传request增加token
    this.headers = {"authorization" : token}
  },
  components:{
    'mybar': mybar
  },
  computed: {
    disabled () {
      return !this.articleMore
    },
    topicDisabled () {
      return !this.topicMore
    },
    draftDisabled () {
      return !this.draftArticleMore
    }
  },
}
</script>
<style scoped>
.delteOrCancel{
  color: #969696;
  font-size:50%;
  margin-right: 10px;
}
.delteOrCancel:hover{
  color: red
}
.myflex{
    display: flex;
    align-items: center;
  }
.detail .user:hover{
  color: black
}
.detail{
  display: flex;
  align-items: center;
  justify-content: space-between;
  color: #8a8a8a
}
.segment{
  border-bottom: 1px solid gray;
}
.user-left{
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.user-left > div{
  margin-right: 20px
}
.user-container{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
.follow-container{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
  padding-bottom: 20px;
  border-bottom: 1px solid #f5f6f7;
}
.out-container{
  width: 50%;
  margin-left: 20%;
  margin-top: 30px
}
.segment:hover .content .title{
  color: red;
}
.segment:hover{
  background-color: #fafafa;
}


.bar-picture{
  display: flex;
  flex-direction: row;
  align-items: center;
}
.ducument-container{
  margin-top: 30px;
}
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  display: block;
}
</style>
