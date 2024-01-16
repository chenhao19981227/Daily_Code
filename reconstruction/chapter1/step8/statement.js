import createStatementData from "./createStatementData.js";
function statement(invoice,plays){
    return renderPlainText(createStatementData(invoice,plays));
}
function renderPlainText(data,plays){
    let result='Statement for ${data.customer}\n';
    for(let perf of data.performances){
        result+=' ${perf.play.name}:${usd(perf.amount/100)}  (${perf.audience}  seats)\n';
    }
    result+= 'Amount owed is ${usd(data.totalAmount)}\n';
    result+= 'You earned ${data.totalVolumeCredits}  credits\n';
    return result;
    function usd(aNumber){
        return  new Intl.NumberFormat("en-US",{
            style:"currency",currency:"USD",minimumFractionDigits:2
        }).format(aNumber);
    }
}
function playFor(aPerformance){
    return plays[aPerformance.playID];
}
function amountFor(aPerformance){//移除非必要参数
    let result = 0;
    switch (aPerformance.play.type){
        case "tragedy":
            result=40000;
            if (aPerformance.audience>30){
                result+=10000*(aPerformance.audience-30);
            }
            break;
        case "comedy":
            result=30000;
            if (aPerformance.audience>20){
                result+=10000+500*(aPerformance.audience-20);
            }
            result+=300*aPerformance.audience;
            break;
        default:
            throw  new Error('unknown type:${play.type}');
    }
    return result;
}
function volumeCreditsFor(perf){
    let result=0;
    result+=Math.max(perf.audience-30,0);
    if("comedy"===perf.play.type) result+=Math.floor(perf.audience/5);
    return result;
}
function totalVolumeCredits(data){
    return data.performances.reduce((total,p)=>total+p.volumeCredits,0);
}
function totalAmount(data){
    return data.performances.reduce((total,p)=>total+p.amount,0);
}
