package com.zuo.kotlindemo.utils

import java.io.File
import java.io.FileFilter
import java.util.*

/**
 * 文件工具类
 *
 * @author zuozhijie
 * @date 2020/7/20 11:49
 */
object FileUtils {
    /**
     * 获取目录下所有过滤的文件
     *
     * @param dir         目录
     * @param filter      过滤器
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    fun listFilesInDirWithFilter(
        dir: File,
        filter: FileFilter,
        isRecursive: Boolean = false
    ): List<File>? {
        if (dir.exists() && !dir.isDirectory) return null
        val list: MutableList<File> = ArrayList()
        val files = dir.listFiles()
        if (files != null && files.size != 0) {
            for (file in files) {
                if (filter.accept(file)) {
                    list.add(file)
                }
                if (isRecursive && file.isDirectory) {
                    listFilesInDirWithFilter(file, filter, true)?.let {
                        list.addAll(it)
                    }
                }
            }
        }
        return list
    }


}