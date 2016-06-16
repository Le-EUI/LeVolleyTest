LOCAL_PATH := $(call my-dir)

# ############预编译##################

# 编译第三方包

############编译apk##################

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

# 依赖工程路径
#dependent_apps_dir := dependent/

# 工具公共库
#common_util_dir := $(dependent_apps_dir)/CommonUtil

# 编译源文件
src_dirs := src \

# java源文件
LOCAL_SRC_FILES := $(call all-java-files-under, $(src_dirs))
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res

                                           
LOCAL_PACKAGE_NAME := LeVolleyTest
LOCAL_CERTIFICATE := platform

# 引用其它包中的资源文件和代码文件，要添加相关包名
LOCAL_AAPT_FLAGS := \
    --auto-add-overlay \


# 添加静态库, 会打包到本应用的class.jar中
# gson
LOCAL_STATIC_JAVA_LIBRARIES := \
    letv-gson-common \
    android-support-v13 \
    android-support-v4 \
    android-common \
    LeVolley \
# http请求，上传文件，下载文件
# LOCAL_STATIC_JAVA_LIBRARIES += asynchttp httpclient aws-core aws
# push服务
# LOCAL_STATIC_JAVA_LIBRARIES += letv.push.sdk

ifeq (1,$(strip $(shell expr $(PLATFORM_VERSION) \>= 6.0)))
    LOCAL_JAVA_LIBRARIES += org.apache.http.legacy.boot
endif

# 指定apk所在目录: /system/priv-app
# LOCAL_PRIVILEGED_MODULE := true

# 是否进行混淆
LOCAL_PROGUARD_ENABLED := full

# 混淆文件
# LOCAL_PROGUARD_FLAG_FILES := proguard.flags

include $(BUILD_PACKAGE)
include $(call all-makefiles-under,$(LOCAL_PATH))
