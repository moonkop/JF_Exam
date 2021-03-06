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
gulp.task('rename-master', function () {
    return gulp.src('templates/components/master.jsp')
        .pipe(rename('components/master.html'))
        .pipe(gulp.dest('temp/'))
})
gulp.task('extend-jsp', function () {
    return gulp.src('temp/**/*.*')
        .pipe(extender({annotations: true, verbose: true, root: '/temp/'}))
        .pipe(insert.prepend("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" pageEncoding=\"UTF-8\"%>"))
        .pipe(rename({extname: ".jsp"}))
        .pipe(gulp.dest("dist/jsp"))
});
gulp.task('clean-up-temp', function () {
    gulp.src('dist/jsp/components')
        .pipe(clean());
    return gulp.src('temp')
        .pipe(clean());
})
gulp.task('jsp', gulp.series('copy-to-temp', 'rename-master', 'extend-jsp', 'clean-up-temp'));

gulp.task('copy-jsp-to-WEB-INF', gulp.series('jsp'), function () {
    return gulp.src('dist/jsp/**/*.*')
        .pipe(gulp.dest('WEB-INF/jsp', {override: false}));
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
gulp.task('minify-css', function () {
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
    return gulp.src(['js/**/*.*'])
        .pipe(gulp.dest('dist/js'))
        .pipe(browserSync.reload({
            stream: true
        }))
})

// Minify JS
gulp.task('minify-js', function () {
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

// Run everything
gulp.task('default', gulp.parallel('minify-css', 'minify-js', 'html', 'jsp'));

// Configure the browserSync task
gulp.task('browserSync', function () {
    browserSync.init({
        server: {
            baseDir: '.'
        },
    })
})
gulp.task("reload", function () {
    console.log("changed");
    browserSync.reload();
})
gulp.task("watch", function () {
    console.log("watching");
    gulp.watch('less/*.less', gulp.series('less'));
    gulp.watch('dist/css/*.css', gulp.series('minify-css'));
    gulp.watch('js/*.js', gulp.series("minify-js"));
    gulp.watch('templates/**/*.*', gulp.series('html'));
    // Reloads the browser whenever HTML or JS files change
    gulp.watch('dist/js/*.js', gulp.series('reload'));
    gulp.watch('dist/css/*.css', gulp.series('reload'));
    gulp.watch('dist/pages/*.*', gulp.series('reload'));

})
// Dev task with browserSync
gulp.task('dev',
    gulp.series( 'less', 'minify-css', 'js', 'minify-js', 'html','browserSync')
   );

