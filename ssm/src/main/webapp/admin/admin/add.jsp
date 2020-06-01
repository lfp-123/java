<%@page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>新增文档 - 光年(Light Year Admin)后台管理系统模板</title>
<link rel="icon" href="../favicon.ico" type="image/ico">
<meta name="keywords" content="LightYear,光年,后台模板,后台管理系统,光年HTML模板">
<meta name="description" content="LightYear是一个基于Bootstrap v3.3.7的后台管理系统的HTML模板。">
<meta name="author" content="yinqi">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/materialdesignicons.min.css" rel="stylesheet">
<!--标签插件-->
<link rel="stylesheet" href="../js/jquery-tags-input/jquery.tagsinput.min.css">
<link href="../css/style.min.css" rel="stylesheet">
</head>

<body>
<div class="container-fluid">

  <div class="row">
    <div class="col-lg-12">
      <div class="card">
        <div class="card-body">

          <form action="/ssm/admin/add" method="post" class="row" enctype="multipart/form-data">
            <div class="form-group col-md-12">
              <label for="name">用户名</label>
              <div class="form-controls">
                <input type="text" class="form-control" id="name" name="account" value="" placeholder="请输入用户名" />
              </div>
            </div>
            <div class="form-group col-md-12">
              <label for="title">姓名</label>
              <input type="text" class="form-control" id="title" name="name" value="" placeholder="姓名" />
            </div>
            <div class="form-group col-md-12">
              <label for="seo_keywords">密码</label>
              <input type="text" class="form-control" id="password" name="password" value="" placeholder="密码" />
            </div>
            <div class="form-group col-md-12">
              <label for="seo_keywords">确认密码</label>
              <input type="text" class="form-control" id="seo_keywords" name="re_password" value="" placeholder="确认密码" />
            </div>
            <div class="form-group col-md-12">
              <label for="phone">手机</label>
              <input class="form-control" id="phone" name="phone" value="" placeholder="手机号"></input>
            </div>
            <div class="form-group col-md-12">
              <label for="email">邮箱</label>
              <input class="form-control" id="email" name="email"  value="" placeholder="邮箱"></input>
            </div>
            <div class="form-group col-md-12">
              <label>头像</label>
              <div class="form-controls">
                <input class="upload" name="imgfile" type="file" onchange=onUpload1(this.files[0])>
                <div class="preview preview1"></div>
                </ul>
              </div>
            </div>
            <div class="form-group col-md-12">
              <label for="sex">性别</label>
              <div class="clearfix">
                <label class="lyear-radio radio-inline radio-primary">
                  <input type="radio" name="sex" value="0"><span>男</span>
                </label>
                <label class="lyear-radio radio-inline radio-primary">
                  <input type="radio" name="sex" value="1" checked><span>女</span>
                </label>
              </div>
            </div>

            <div class="form-group col-md-12">
              <label for="status">状态</label>
              <div class="clearfix">
                <label class="lyear-radio radio-inline radio-primary">
                  <input type="radio" name="status" value="0"><span>禁用</span>
                </label>
                <label class="lyear-radio radio-inline radio-primary">
                  <input type="radio" name="status" value="1" checked><span>启用</span>
                </label>
              </div>
            </div>
            <div class="form-group col-md-12">
              <button type="submit" class="btn btn-primary ajax-post" target-form="add-form">确 定</button>
              <button type="button" class="btn btn-default" onclick="javascript:history.back(-1);return false;">返 回</button>
            </div>
          </form>

        </div>
      </div>
    </div>
  </div>

</div>

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/perfect-scrollbar.min.js"></script>
<!--标签插件-->
<script src="../js/jquery-tags-input/jquery.tagsinput.min.js"></script>
<script type="text/javascript" src="../js/main.min.js"></script>

<script type="text/javascript">
    function onUpload1 (file) {
        var fr = new FileReader();
        fr.readAsDataURL(file);  // 将文件读取为Data URL
        fr.onload = function(e) {
            var result = e.target.result;
            if (/image/g.test(file.type)) {
                var img = $('<img src="' + result + '">');
                $('.preview1').html('').append(img);

            } else if (/video/g.test(file.type)) {
                var vidoe = $('<video controls src="' + result + '">');
                $('.preview1').html('').append(vidoe);
            }
        }
    }

</script>
</body>
</html>