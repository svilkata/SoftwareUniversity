import { html } from '../lib.js';
import { register} from '../api/data.js';

const registerTemplate = (onSubmit, errorMsg) => html`
<section id="register">
    <article class="narrow">
        <header class="pad-med">
            <h1>Register</h1>
        </header>
        <form @submit=${onSubmit} id="register-form" class="main-form pad-large">
            ${errorMsg ? html`<div class="error">${errorMsg}</div>` : ''}          
            <label>E-mail: <input type="text" name="email"></label>
            <label>Username: <input type="text" name="username"></label>
            <label>Password: <input type="password" name="password"></label>
            <label>Repeat: <input type="password" name="repass"></label>
            <input class="action cta" type="submit" value="Create Account">
        </form>
        <footer class="pad-small">Already have an account? <a href="/login" class="invert">Sign in here</a>
        </footer>
    </article>
</section>
`;


export async function registerPage(ctx) {

    ctx.renderProp(registerTemplate(onSubmit));

    async function onSubmit(event) {
        event.preventDefault();

        const formData = new FormData(event.target);
        const email = formData.get('email').trim();
        const username = formData.get('username').trim();
        const password = formData.get('password').trim();
        const repass = formData.get('repass').trim();

        try {
            if (email == '' || username == '' || password == '') {
                throw new Error('All fields are required!')
            }

            if (password != repass) {
                throw new Error('Passwords don\'t match!');
            }

            await register(email, username, password);
            ctx.setUserNavProp();
            ctx.page.redirect('/my-teams');
        } catch (err) {
            ctx.renderProp(registerTemplate(onSubmit, err.message));
        }
    }
}