
function timerUtils(interval,callback) {
  this.timeoutId = null;
  this.interval = interval;
  this.callback = callback;
}

timerUtils.prototype.start = function (){

  var util = this;

  util.timeoutId = setTimeout(function callee(){

    util.callback();
    if(util.timeoutId === null) return;
    util.timeoutId = setTimeout(callee,util.interval);

  },util.interval);
}

timerUtils.prototype.stop = function (){

  var util = this;

  if(util.timeoutId !== null){

    clearTimeout(util.timeoutId);
    util.timeoutId = null;
  }
}
