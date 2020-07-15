export default class Cropper {
    constructor(options) {
        this.holderNode = document.querySelector(options.holderNode);
        this.el = this.holderNode.querySelector(options.imageNode);
        this.state = {};
        this.ratio = 1;
        this.touchDevice = 'ontouchstart' in window || navigator.maxTouchPoints;
        this.options = options;

        this.imageHolder = this.el.querySelector('.js-cropper-image-box-holder');
        this.imageNode = this.el.querySelector('.js-cropper-image');

        this.thumbNode = this.el.querySelector(options.thumbNode);
        this.spinnerNode = this.el.querySelector(options.spinner);
        this.zoomInButton = this.el.querySelector(options.zoomIn);
        this.zoomOutButton = this.el.querySelector(options.zoomOut);
        this.image = new Image();
        this.zoomIn = this.zoomIn.bind(this);
        this.zoomOut = this.zoomOut.bind(this);

        this.el.classList.add('cropper-loading');
        this.image.onload = () => {
            this.el.classList.remove('cropper-loading');
            this.setBackground();
            // const mousewheel = (/Firefox/i.test(navigator.userAgent))? 'DOMMouseScroll' : 'mousewheel';
            // this.el.addEventListener(mousewheel, this.zoomImage.bind(this));
        };
        this.zoomInButton.addEventListener('click', this.zoomIn);
        this.zoomOutButton.addEventListener('click', this.zoomOut);

        if (typeof options.imgSrc === 'object') {
            this.image = options.imgSrc;
        } else {
            this.image.src = options.imgSrc;
        }

        return this;
    }

    zoomIn() {
        this.ratio *= 1.1;
        this.setBackground();
    }

    zoomOut() {
        this.ratio *= 0.9;
        this.setBackground();
    }

    setBackground() {
        this.imageNode.src = this.image.src;
        const scaleRegex = /scale\(.+\d+\)/g;

          const newScale = 'scale(' + this.ratio + ')';
          if (this.imageNode.style.transform.match(scaleRegex)) {
            this.imageNode.style.transform = this.imageNode.style.transform.replace(scaleRegex, newScale);  
          } else {
            this.imageNode.style.transform = this.imageNode.style.transform + ' ' + newScale;
          }
    }

  
    getCoordinatesFromTransformString(string) {
        const translateRegex = /translate((-|.)\d{0,10}px,\W(-|.)\d{0,10}px)/g;
        const match = string.match(translateRegex);
        if (typeof string === 'string' && match) {
          return match[0].match(/\d{0,10}px,\W(-|.)\d{0,10}px/)[0].split(',').map(singleStringNumber => parseInt(singleStringNumber, 10));
        } else {
          return [0, 0];
        }
    }

    zoomImage(event) {
        var delta = event.detail ? event.detail * (-120) : event.wheelDelta;
        if (delta > -120) {
            this.ratio *= 1.1;
        } else {
            this.ratio *= 0.9;
        }

        this.setBackground();
    }

    show() {
        this.holderNode.removeAttribute('hidden');
    }

    hide() {
        this.holderNode.setAttribute('hidden', 'hidden');
    }
}