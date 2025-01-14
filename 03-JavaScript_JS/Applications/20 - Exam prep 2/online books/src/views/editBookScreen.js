import { editBook, getBookById } from '../api/data.js';
import { html } from '../lib.js';

const editBookTemplate = (book, onSubmit) => html`
<!-- Edit Page ( Only for the creator )-->
<section id="edit-page" class="edit">
    <form @submit=${onSubmit} id="edit-form" action="#" method="">
        <fieldset>
            <legend>Edit my Book</legend>
            <p class="field">
                <label for="title">Title</label>
                <span class="input">
                    <input type="text" name="title" id="title" .value=${book.title}>
                </span>
            </p>
            <p class="field">
                <label for="description">Description</label>
                <span class="input">
                    <textarea name="description"
                        id="description" .value=${book.description}></textarea>
                </span>
            </p>
            <p class="field">
                <label for="image">Image</label>
                <span class="input">
                    <input type="text" name="imageUrl" id="image" .value=${book.imageUrl}>
                </span>
            </p>
            <p class="field">
                <label for="type">Type</label>
                <span class="input">
                    <select id="type" name="type" .value=${book.type}>
                        <option value="Fiction" selected>Fiction</option>
                        <option value="Romance">Romance</option>
                        <option value="Mistery">Mistery</option>
                        <option value="Classic">Clasic</option>
                        <option value="Other">Other</option>
                    </select>
                </span>
            </p>
            <input class="button submit" type="submit" value="Save">
        </fieldset>
    </form>
</section>`;


export async function editBookPage(ctx) {
    const book = await getBookById(ctx.params.id);
    ctx.renderProps(editBookTemplate(book, onSubmit));

    async function onSubmit(event) {
        event.preventDefault();
        const formData = new FormData(event.target);
        //debug
        console.log(...formData.entries());

        const title = formData.get('title').trim();
        const description = formData.get('description').trim();
        const imageUrl = formData.get('imageUrl').trim();
        const type = formData.get('type').trim();

        // formData.values().some(v => v == '');  //при повече полета за по-кратък запис
        if (title == '' || description == '' || imageUrl == '') {
            return alert('Please, fill in all fields');
        }

        await editBook(ctx.params.id, {
            title: title,
            description: description,
            imageUrl: imageUrl,
            type: type
        });

        ctx.page.redirect('/details/' + ctx.params.id);
    }
}