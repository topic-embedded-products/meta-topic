#!/bin/sh
case "$1" in
  start)
    /usr/sbin/haveged --once 2>/dev/null &
    ;;
esac
