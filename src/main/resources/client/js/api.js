const stockList = document.querySelector('.stockList');
const stockListCompiler = Handlebars.compile(stockList.innerHTML);

const pageContent = document.querySelector('.mainContent');

const addButton = document.querySelector('.add');

const addStock = document.querySelector('.addStock');
const addStockCompiler = Handlebars.compile(addStock.innerHTML);

const updateStock = document.querySelector('.updateStock');

const eatButton = document.querySelector('.eat');

const getStock = () => {
    axios.get('/api/get').then(response => {
        console.log(response.data);
        let showHtml = stockListCompiler({ stock: response.data })
        pageContent.innerHTML = showHtml;
    })
}

window.onload = () => {
    getStock();
}

addButton.addEventListener('click', () => {
    let showHtml = addStockCompiler(addStock)
    pageContent.innerHTML = showHtml;
})

updateStock.addEventListener('click', () => {
    const item = document.querySelector('.item');
    const qty = document.querySelector('.qty');
    let params = {
        "item": item.value,
        "qty": qty.value
    }

    axios.post('/add_choco', params).then(results => {
    })
})

eatButton.addEventListener('click', () => { 
    
})