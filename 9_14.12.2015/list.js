function cons(a, b) {
    return function(func) {
        return func(a, b);
    }
}

function car(tuple) {
    return tuple(function(a, b) {
        return a;
    });
}

function cdr(tuple) {
    return tuple(function(a, b) {
        return b;
    });
}

function forEach(list, func) {
    func(car(list));
    var tail = cdr(list);
    if (typeof tail === 'function') {
        forEach(tail, func);
    } else {
        func(tail);
    }
}

function map(list, func) {
    var head = car(list);
    var tail = cdr(list);
    if (typeof tail === 'function') {
        console.log(forEach(tail, log));
        return cons(func(head), map(tail, func));
    } else {
        return cons(func(head), func(tail));
    }
}

function filter(list, func) {
    var head = car(list);
    var tail = cdr(list);
    if (typeof tail === 'function') {
        if (func(head)) {
            return cons(head, filter(tail, func));
        } else {
            return filter(tail, func);
        }
    } else {
        if (func(head)) {
            if (func(tail)) {
                console.log("here");
                return cons(head, tail);
            } else {
                return head;
            }
        } else {
            if (func(tail)) {
                console.log("here2");
                return cons(tail, 0);
            }
        }
    }
}

function reduce(list, func) {
    var head = car(list);
    var tail = cdr(list);
    var acc = 0;
    if (typeof tail === 'function') {
       acc += func(acc, head);
       acc += reduce(tail, func);
        return acc;
    } else {
        acc += func(acc, head + tail);
        return acc;
    }
}

var list = cons(1, cons(2, cons(3, cons(4, 5))));

var sum = reduce(list, function (accumulation, current) {
    return accumulation + current;
}, 0);

console.log(sum);