<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('partner_certificate', function (Blueprint $table) {
            $table->unsignedBigInteger('partner_id')->comment('파트너 고유키');
            $table->unsignedBigInteger('certificate_id')->comment('자격증 고유키');
            $table->dateTime('created_at')->index()->comment('생성일시');

            $table->unique(['partner_id', 'certificate_id'], 'p_id_c_id_unique');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('partner_certificate');
    }
};
