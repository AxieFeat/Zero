package net.zero.common

import net.zero.common.startup.ArgumentBuilder

// TODO General fields for client and server.
interface ZeroBase {

    fun start(arguments: ArgumentBuilder)

}