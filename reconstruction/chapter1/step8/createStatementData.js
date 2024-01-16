export default function createStatementData(invoice,plays){
    const statementData={};
    statementData.customer=invoice.customer;
    statementData.performances=invoice.performances.map(enrichPerformance);
    statementData.totalAmount=totalAmount(statementData);
    statementData.totalVolumeCredits=totalVolumeCredits(statementData);
    return statementData;
}
function enrichPerformance(aPerformance){
    const result = Object.assign({},aPerformance);
    result.play=playFor(aPerformance);
    result.amount=amountFor(aPerformance);
    result.volumeCredits=volumeCreditsFor(result);
    return result;
}
