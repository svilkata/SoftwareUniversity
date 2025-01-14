const element = document.createElement('div');
element.id = 'overlay';

element.innerHTML = `
<div id="modal"> 
    <p>Are you sure</p>
    <div>
        <button class="modal-ok">Ok</button>
        <button class="modal-cancel">Cancel</button>
    </div>
</div>`;

element.querySelector('.modal-ok').addEventListener('click', () => onChoice(true));
element.querySelector('.modal-cancel').addEventListener('click', () => onChoice(false));

const msg = element.querySelector('p');

let onChoice = null;

export function showModal(message) {
    msg.textContent = message;
    document.body.appendChild(element);

    //връщане на резултат с Promise
    return new Promise(resolveCallback => {
        onChoice = (choice) => {
            clear();
            resolveCallback(choice);
        }
    });
}

export function clear() {
    element.remove();
}