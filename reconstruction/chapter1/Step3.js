function statement (invoice,plays){
    function playFor(aPerformance){
        return plays[aPerformance.playID];
    }
    function amountFor(aPerformance){//移除非必要参数
        let result = 0;
        switch (playFor(aPerformance).type){
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
        if("comedy"===playFor(perf).type) result+=Math.floor(perf.audience/5);
        return result;
    }
    let totalAmount =0;
    let volumeCredits=0;
    let result='Statement for ${invoice.customer}\n';
    const format = new Intl.NumberFormat("en-US",{
        style:"currency",currency:"USD",minimumFractionDigits:2
    }).format;
    for(let perf of invoice.performances){
        volumeCredits+=volumeCreditsFor(perf);

        result+=' ${playFor(perf).name}:${format(amountFor(perf)/100)}  (${perf.audience}  seats)\n';
        totalAmount+=amountFor(perf);
    }
    result+= 'Amount owed is ${format(totalAmount/100)}\n';
    result+= 'You earned ${volumeCredits}  credits\n';
    return result;
}
