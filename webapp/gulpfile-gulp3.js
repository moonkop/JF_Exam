var gulp = require('gulp');
var less = require('gulp-less');
var browserSync = require('browser-sync').create();
var header = require('gulp-header');
var cleanCSS = require('gulp-clean-css');
var rename = require("gulp-rename");
var uglify = require('gulp-uglify');
var pkg = require('./package.json');
var sourcemaps = require('gulp-sourcemaps');
var extender = require('gulp-html-extend');
var insert = require('gulp-insert');
var gulpFilter = require('gulp-filter');
var clean = require('gulp-clean');



//gulp.task('jsp', ['copy-to-temp', 'rename-master', 'extend-jsp', 'clean-temp'])
// gulp.task('jsp1', function () {
//     gulp.src('templates/**/*.*')
//         .pipe(gulp.dest('temp/'))
//         // .pipe(gulp.src('templates/components/masterjsp.html'))
//         // .pipe(rename('components/master.html'))
//         // .pipe(gulp.dest('temp/'))
//         // .pipe(gulp.src('temp/**/*.*'))
//         // .pipe(extender({annotations: true, verbose: true, root: '/temp/'}))
//         // .pipe(insert.prepend("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" pageEncoding=\"UTF-8\"%>"))
//         // .pipe(rename({extname: ".jsp"}))
//         // .pipe(gulp.dest("dist/jsp"))
//         // .pipe(gulp.src('temp'))
//         // .pipe(clean());
// });


gulp.task('copy-to-temp', function () {
    return gulp.src('templates/**/*.*')
    //  .pipe(rename('components/master.html'))
        .pipe(gulp.dest('temp/'))
})
gulp.task('rename-master',['copy-to-temp'], function () {
    return gulp.src('templates/components/master.jsp')
        .pipe(rename('components/master.html'))
        .pipe(gulp.dest('temp/'))
})
gulp.task('extend-jsp',['rename-master'], function () {
    return gulp.src('temp/**/*.*')
        .pipe(extender({annotations: true, verbose: true, root: '/temp/'}))
        .pipe(insert.prepend("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" pageEncoding=\"UTF-8\"%>"))
        .pipe(rename({extname: ".jsp"}))
        .pipe(gulp.dest("dist/jsp"))
});
gulp.task('jsp',['extend-jsp'], function () {
    gulp.src('dist/jsp/components')
        .pipe(clean());
    return gulp.src('temp')
        .pipe(clean());
});

gulp.task('copy-jsp-to-WEB-INF',['jsp'],function () {
     return gulp.src('dist/jsp/**/*.*')
         .pipe(gulp.dest('WEB-INF/jsp',{override:false}));
})


//generate html form components
gulp.task('html', function () {
    const filter = gulpFilter(['**', '!/templates/components'])
    return gulp.src('templates/**/*.html')
        .pipe(extender({annotations: true, verbose: true, root: '/templates/'}))
        .pipe(filter)
        .pipe(gulp.dest('dist/pages'))
        .pipe(browserSync.reload({
            stream: true
        }))
})





// Compile LESS files from /less into /css
gulp.task('less', function () {
    return gulp.src('less/sb-admin-2.less')
        .pipe(sourcemaps.init())
        .pipe(less())
        .pipe(sourcemaps.write())
        .pipe(gulp.dest('dist/css'))
        .pipe(browserSync.reload({
            stream: true
        }))
});

// Minify compiled CSS
gulp.task('minify-css', ['less'], function () {
    return gulp.src('dist/css/sb-admin-2.css')
        .pipe(cleanCSS({compatibility: 'ie8'}))
        .pipe(rename({suffix: '.min'}))
        .pipe(gulp.dest('dist/css'))
        .pipe(browserSync.reload({
            stream: true
        }))
});

// Copy JS to dist
gulp.task('js', function () {
    return gulp.src(['js/sb-admin-2.js'])
        .pipe(gulp.dest('dist/js'))
        .pipe(browserSync.reload({
            stream: true
        }))
})

// Minify JS
gulp.task('minify-js', ['js'], function () {
    return gulp.src('js/sb-admin-2.js')
        .pipe(uglify(
            {
                mangle: true,//类型：Boolean 默认：true 是否修改变量名
                compress: true,//类型：Boolean 默认：true 是否完全压缩
                preserveComments: 'all' //保留所有注释
            }
        ))
        .pipe(rename({suffix: '.min'}))
        .pipe(gulp.dest('dist/js'))
        .pipe(browserSync.reload({
            stream: true
        }))
});

// Copy vendor libraries from /bower_components into /vendor
gulp.task('copy', function () {
    gulp.src(['bower_components/bootstrap/dist/**/*', '!**/npm.js', '!**/bootstrap-theme.*', '!**/*.map'])
        .pipe(gulp.dest('vendor/bootstrap'))

    gulp.src(['bower_components/bootstrap-social/*.css', 'bower_components/bootstrap-social/*.less', 'bower_components/bootstrap-social/*.scss'])
        .pipe(gulp.dest('vendor/bootstrap-social'))

    gulp.src(['bower_components/datatables/media/**/*'])
        .pipe(gulp.dest('vendor/datatables'))

    gulp.src(['bower_components/datatables-plugins/integration/bootstrap/3/*'])
        .pipe(gulp.dest('vendor/datatables-plugins'))

    gulp.src(['bower_components/datatables-responsive/css/*', 'bower_components/datatables-responsive/js/*'])
        .pipe(gulp.dest('vendor/datatables-responsive'))

    gulp.src(['bower_components/flot/*.js'])
        .pipe(gulp.dest('vendor/flot'))

    gulp.src(['bower_components/flot.tooltip/js/*.js'])
        .pipe(gulp.dest('vendor/flot-tooltip'))

    gulp.src(['bower_components/font-awesome/**/*', '!bower_components/font-awesome/*.json', '!bower_components/font-awesome/.*'])
        .pipe(gulp.dest('vendor/font-awesome'))

    gulp.src(['bower_components/jquery/dist/jquery.js', 'bower_components/jquery/dist/jquery.min.js'])
        .pipe(gulp.dest('vendor/jquery'))

    gulp.src(['bower_components/metisMenu/dist/*'])
        .pipe(gulp.dest('vendor/metisMenu'))

    gulp.src(['bower_components/morrisjs/*.js', 'bower_components/morrisjs/*.css', '!bower_components/morrisjs/Gruntfile.js'])
        .pipe(gulp.dest('vendor/morrisjs'))

    gulp.src(['bower_components/raphael/raphael.js', 'bower_components/raphael/raphael.min.js'])
        .pipe(gulp.dest('vendor/raphael'))

})

// Run everything
gulp.task('default', ['minify-css', 'minify-js', 'copy', 'html', 'jsp']);

// Configure the browserSync task
gulp.task('browserSync', function () {
    browserSync.init({
        server: {
            baseDir: '.'
        },
    })
})

// Dev task with browserSync
gulp.task('dev', ['browserSync', 'less', 'minify-css', 'js', 'minify-js', 'html'], function () {
    gulp.watch('less/*.less', ['less']);
    gulp.watch('dist/css/*.css', ['minify-css']);
    gulp.watch('js/*.js', ['minify-js']);
    gulp.watch('templates/**/*.*', ['html']);
    // Reloads the browser whenever HTML or JS files change
    gulp.watch('dist/js/*.js', browserSync.reload);
    gulp.watch('dist/css/*.css', browserSync.reload);
    gulp.watch('dist/pages/*.*', browserSync.reload);

});
